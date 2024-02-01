# VPC, Subnets
module "network" {
    source = "../modules/network"
    
}

module "rds" {
  source = "../modules/rds"
  RAZORPAY_ACS_VPC_ID = module.network.vpc_id
  AWS_DB_SUBNET_GROUP_NAME = module.network.db_subnet_group_name
  NAT_GATEWAY_ID = module.network.gateway_id
  DB_SUBNET_0_ID = module.network.dbsubnet_0_id
  DB_SUBNET_1_ID =  module.network.dbsubnet_1_id
  DB_SUBNET_2_ID =  module.network.dbsubnet_2_id
  BASTION_SSH_ID = module.network.bastion_ssh_id
}
  
  

module "eks" {
  source = "../modules/eks"
  EKS_DATPLANE_SUBNET = module.network.eks_dataplane_subet
  EKS_PUBLIC_SUBNET = module.network.eks_public_subet
  RAZORPAY_ACS_VPC_ID = module.network.vpc_id
  NAT_GATEWAY_ID = module.network.gateway_id
  # depends_on = [ module.rds ]
}

module "bastion" {
  source = "../modules/bastion"
  EKS_PUBLIC_SUBNET = module.network.eks_public_subet
  BASTION_SSH_ID = module.network.bastion_ssh_id
}

module "cloudhsm" {
  source = "../modules/cloudhsm"
  DB_PRIVATE_SUBNET = module.network.db_private_subet
  HSM_SUBNET_ID = module.network.dbsubnet_1_id
}


module "nginx-controller" {
  source  = "terraform-iaac/nginx-controller/helm"

  additional_set = [
    {
      name  = "controller.service.annotations.service\\.beta\\.kubernetes\\.io/aws-load-balancer-type"
      value = "nlb"
      type  = "string"
    },
    {
      name  = "controller.service.annotations.service\\.beta\\.kubernetes\\.io/aws-load-balancer-internal"
      value = "true"
      type  = "string"
    },
    {
      name  = "controller.service.annotations.service\\.beta\\.kubernetes\\.io/aws-load-balancer-cross-zone-load-balancing-enabled"
      value = "true"
      type  = "string"
    }
  ]
  # depends_on = [ module.eks ]
}

data "kubernetes_service" "ingress-nginx" {
  metadata {
    name = "ingress-nginx-controller"
    namespace = module.nginx-controller.namespace
  }
  depends_on = [module.nginx-controller]
}



data "aws_lb" "nlb" {
 # provider = aws.network-role
  name     = split("-", split(".", data.kubernetes_service.ingress-nginx.status.0.load_balancer.0.ingress.0.hostname).0).0
  depends_on = [ data.kubernetes_service.ingress-nginx ]
}

module "api-gateway" {
  source = "../modules/api-gateway-vpclink"
  RAZORPAY_ACS_VPC_ID = module.network.vpc_id
  AWS_NLB_ARN = data.aws_lb.nlb.arn
  NGINX_LB_HOSTNAME = data.kubernetes_service.ingress-nginx.status[0].load_balancer[0].ingress[0].hostname
  depends_on = [module.nginx-controller]
}

provider "kubernetes" {
  host                   = module.eks.eks_cluster_endpoint
  cluster_ca_certificate = base64decode(module.eks.eks_cluster_certificates)
  exec {
    api_version = "client.authentication.k8s.io/v1"
    args        = ["eks", "get-token", "--cluster-name", module.eks.eks_cluster_name]
    command     = "aws"
  }
}


provider "helm" {
  kubernetes {
    host                   = module.eks.eks_cluster_endpoint
    cluster_ca_certificate = base64decode(module.eks.eks_cluster_certificates)
    exec {
      api_version = "client.authentication.k8s.io/v1"
      args        = ["eks", "get-token", "--cluster-name", module.eks.eks_cluster_name]
      command     = "aws"
    }
  }
}

### EKS Pod Security ###
###########################################################################################
##
## Reference - https://registry.terraform.io/providers/hashicorp/kubernetes/latest/docs/resources/pod_security_policy
## Reference - https://docs.aws.amazon.com/eks/latest/userguide/pod-security-policy.html
## Validates pod security before pod creation on these conditions
## Deny creating pods with '.spec.hostIPC' set to true.
## Deny pods with '.spec.hostNetwork' set to true.
## Deny pods with '.spec.hostPID' set to true
## Deny pods with '.spec.<containers>.securityContext.allowPrivilegeEscalation' set to true
## Deny pods with  '.spec.<containers>.securityContext.privileged' set to true. 
##
###########################################################################################

## Installing the gatekeeper
resource "helm_release" "gatekeeper" {
  name       = "gatekeeper"
  repository = "https://open-policy-agent.github.io/gatekeeper/charts"
  chart      = "gatekeeper"
  version    = "3.12.0" # or specify a specific version

   depends_on = [
    module.eks
  ]
}

## Installing all the constraint templates  
resource "helm_release" "gatekeeper-templates" {
  chart     = "${path.root}/../../k8s-gatekeeper/helm-gatekeeper-templates"
  name      = "gatekeeper-templates"
  version   = "0.0.3"

  depends_on = [
    helm_release.gatekeeper
  ]
}

## Installing all the constraints  
resource "helm_release" "gatekeeper-constraints" {
  chart     = "${path.root}/../../k8s-gatekeeper/helm-gatekeeper-constraints"
  name      = "gatekeeper-constraints"
  version   = "0.0.3"

  depends_on = [
    helm_release.gatekeeper-templates
  ]
}

####################################################################################
####################################################################################
####################################################################################
####################################################################################

module "acs_application" {
  source = "../../application"
  RDS_ENDPOINT = module.rds.rds_endpoint
  RDS_USER = module.rds.rds_username
  RDS_PWD = module.rds.rds_password
}