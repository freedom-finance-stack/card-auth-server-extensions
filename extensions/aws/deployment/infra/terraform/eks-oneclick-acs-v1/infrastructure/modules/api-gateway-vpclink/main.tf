# module "nginx-controller" {
#   source  = "terraform-iaac/nginx-controller/helm"

#   additional_set = [
#     {
#       name  = "controller.service.annotations.service\\.beta\\.kubernetes\\.io/aws-load-balancer-type"
#       value = "nlb"
#       type  = "string"
#     },
#     {
#       name  = "controller.service.annotations.service\\.beta\\.kubernetes\\.io/aws-load-balancer-internal"
#       value = "true"
#       type  = "string"
#     },
#     {
#       name  = "controller.service.annotations.service\\.beta\\.kubernetes\\.io/aws-load-balancer-cross-zone-load-balancing-enabled"
#       value = "true"
#       type  = "string"
#     }
#   ]
# }

# data "kubernetes_service" "ingress-nginx" {
#   metadata {
#     name = "ingress-nginx-controller"
#     namespace = module.nginx-controller.namespace
#   }
#   depends_on = [module.nginx-controller]
# }
# # output "load_balancer_hostname" {
# #   value = data.kubernetes_service.ingress-nginx.status[0].load_balancer[0].ingress[0].hostname

# # }


# data "aws_lb" "nlb" {
#  # provider = aws.network-role
#   name     = split("-", split(".", data.kubernetes_service.ingress-nginx.status.0.load_balancer.0.ingress.0.hostname).0).0
#   depends_on = [ data.kubernetes_service.ingress-nginx ]
# }


### API Gatewat and VPC Link

resource "aws_api_gateway_rest_api" "main" {
 name = "acs_apis"
 description = "ACS Gateway used for EKS. Managed by Terraform."
 endpoint_configuration {
   types = ["REGIONAL"]
 }
}


resource "aws_api_gateway_resource" "razorpay" {
  rest_api_id = aws_api_gateway_rest_api.main.id
  parent_id   = aws_api_gateway_rest_api.main.root_resource_id
  path_part   = "razorpay"
}

resource "aws_api_gateway_resource" "proxy" {
  rest_api_id = aws_api_gateway_rest_api.main.id
  parent_id   = aws_api_gateway_resource.razorpay.id
  path_part   = "{proxy+}"
}

resource "aws_api_gateway_method" "proxy" {
  rest_api_id   = aws_api_gateway_rest_api.main.id
  resource_id   = aws_api_gateway_resource.proxy.id
  http_method   = "ANY"
  authorization = "NONE"

  request_parameters = {
    "method.request.path.proxy"           = true
    "method.request.header.Authorization" = true
  }
}

resource "aws_api_gateway_integration" "proxy" {
  rest_api_id = aws_api_gateway_rest_api.main.id
  resource_id = aws_api_gateway_resource.proxy.id
  http_method = "ANY"

  integration_http_method = "ANY"
  type                    = "HTTP_PROXY"
  uri                     = "http://${var.NGINX_LB_HOSTNAME}/{proxy}"
  passthrough_behavior    = "WHEN_NO_MATCH"
  content_handling        = "CONVERT_TO_TEXT"

  request_parameters = {
    "integration.request.path.proxy"           = "method.request.path.proxy"
    "integration.request.header.Accept"        = "'application/json'"
    "integration.request.header.Authorization" = "method.request.header.Authorization"
  }

  connection_type = "VPC_LINK"
  connection_id   = aws_api_gateway_vpc_link.vpc_link.id
}

resource "aws_api_gateway_deployment" "main" {
  rest_api_id = aws_api_gateway_rest_api.main.id

  triggers = {
    redeployment = sha1(jsonencode(aws_api_gateway_rest_api.main.body))
  }

  lifecycle {
    create_before_destroy = true
  }

  depends_on = [
        aws_api_gateway_method.proxy,
        aws_api_gateway_integration.proxy
      ]
}

resource "aws_api_gateway_stage" "main" {
  deployment_id = aws_api_gateway_deployment.main.id
  rest_api_id   = aws_api_gateway_rest_api.main.id
  stage_name    = "dev"
}

output "acs_api_uri" {
  value       = aws_api_gateway_stage.main.invoke_url
  description = "API URI"
}
resource "aws_api_gateway_vpc_link" "vpc_link" {
  name = "aws_vpc_link"
  #target_arns = [data.kubernetes_service.ingress-nginx.status[0].load_balancer[0].ingress[0].hostname]
  target_arns = [var.AWS_NLB_ARN]
}

