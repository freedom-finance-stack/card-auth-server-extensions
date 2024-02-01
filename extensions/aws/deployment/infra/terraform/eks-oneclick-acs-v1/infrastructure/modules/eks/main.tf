



#Step 1 - The following IAM role will be attached to EKS cluster during its creation.check "name" 
resource "aws_iam_role" "razorpay-acs-eks-cluster-role" {
  name = "razorpay-eks-cluster-role"

  assume_role_policy = <<POLICY
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "eks.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
POLICY
}

# Step 2 - Create a role policy attachment for the IAM to the above role and attach AmazonEKSCluster policy
resource "aws_iam_role_policy_attachment" "razorpay-ecs-AmazonEKSClusterPolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy"
  role       = aws_iam_role.razorpay-acs-eks-cluster-role.name

}

#Create a cloud watch log group for the cluster
#resource "aws_cloudwatch_log_group" "razorpay-acs-loggroup" {
#  # The log group name format is /aws/eks/<cluster-name>/cluster
# Reference: https://docs.aws.amazon.com/eks/latest/userguide/control-plane-logs.html
#  name              = "/aws/eks/${var.eks-cluster-name}/cluster"
#  retention_in_days = 7

# ... potentially other configuration ...
#}


#Start creating the cluster
resource "aws_eks_cluster" "razorpay-acs-eks-cluster" {

  name = var.EKS_CLUSTER_NAME

  role_arn = aws_iam_role.razorpay-acs-eks-cluster-role.arn

  enabled_cluster_log_types = ["api", "audit"]

  vpc_config {

    endpoint_private_access = false
    endpoint_public_access  = true

    subnet_ids = [
      var.EKS_DATPLANE_SUBNET[0],
      var.EKS_DATPLANE_SUBNET[1],
      var.EKS_DATPLANE_SUBNET[2],
      var.EKS_PUBLIC_SUBNET[0],
      var.EKS_PUBLIC_SUBNET[1],
      var.EKS_PUBLIC_SUBNET[2]


    ]

  }
  # depends_on = [aws_iam_role_policy_attachment.razorpay-ecs-AmazonEKSClusterPolicy, aws_db_instance.acs-mysql-db]
  depends_on = [aws_iam_role_policy_attachment.razorpay-ecs-AmazonEKSClusterPolicy]
}




### Data source for AWS EKS Cluster
data "aws_eks_cluster" "cluster" {
    name = aws_eks_cluster.razorpay-acs-eks-cluster.name
}
data "aws_eks_cluster_auth" "cluster" {
    name = aws_eks_cluster.razorpay-acs-eks-cluster.name
}
### EKS Cluster Addons ###
##########################

resource "aws_eks_addon" "vpc-cni" {
  cluster_name                = aws_eks_cluster.razorpay-acs-eks-cluster.name
  addon_name                  = "vpc-cni"
  resolve_conflicts_on_create = "OVERWRITE"
  resolve_conflicts_on_update = "OVERWRITE"
}


resource "aws_eks_addon" "kube-proxy" {
  cluster_name                = aws_eks_cluster.razorpay-acs-eks-cluster.name
  addon_name                  = "kube-proxy"
  resolve_conflicts_on_create = "OVERWRITE"
  resolve_conflicts_on_update = "OVERWRITE"

}

resource "aws_eks_addon" "core-dns" {
  cluster_name                = aws_eks_cluster.razorpay-acs-eks-cluster.name
  addon_name                  = "coredns"
  resolve_conflicts_on_create = "OVERWRITE"
  resolve_conflicts_on_update = "OVERWRITE"

}

### EKS Worker Nodes ###
##########################

# Create an IAM role for eks worker nodes
resource "aws_iam_role" "razorpay-acs-eks-worker-role" {
  name = "razorpay-acs-eks-worker-role"

  assume_role_policy = jsonencode({
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ec2.amazonaws.com"
      }
    }]
    Version = "2012-10-17"
  })
}

#Attach AmazonEKSWorkerNodePolicy to razorpay-acs-eks-worker-role
resource "aws_iam_role_policy_attachment" "razorpay-acs-nodes-AmazonEKSWorkerNodePolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"
  role       = aws_iam_role.razorpay-acs-eks-worker-role.name
}

#Attach AmazonEKS_CNI_POILICY to razorpay-acs-eks-worker-role
resource "aws_iam_role_policy_attachment" "razorpay-acs-nodes-AmazonEKS_CNI_Policy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy"
  role       = aws_iam_role.razorpay-acs-eks-worker-role.name
}

#Attach AmazonEC2ContainerRegistryReadOnly to worker nodes

resource "aws_iam_role_policy_attachment" "razorpay-acs-nodes-AmazonEC2ContainerRegistryReadOnly" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
  role       = aws_iam_role.razorpay-acs-eks-worker-role.name
}

# Let's create create eks nodes for private node group

resource "aws_eks_node_group" "acs-eks-private-nodes" {


  # Set the basic metadata property for the cluster
  cluster_name    = aws_eks_cluster.razorpay-acs-eks-cluster.name
  node_group_name = "razorpay-acs-private-nodes"
  node_role_arn   = aws_iam_role.razorpay-acs-eks-worker-role.arn


  # The following lines will place worker nodes in the private subnets in given region such as 
  # 1a : 10.0.4.0/24
  # 1b : 10.0.5.0/24
  # 1c : 10.0.6.0/24

  subnet_ids = [
    var.EKS_DATPLANE_SUBNET[0],
    var.EKS_DATPLANE_SUBNET[1],
    var.EKS_DATPLANE_SUBNET[2],

  ]

  # Selecting t3.medium
  capacity_type  = "ON_DEMAND"
  instance_types = var.EKS_WORKER_INSTANCE_TYPE


  # disk size
  disk_size = 40


  force_update_version = false

  # Define the scale config
  scaling_config {
    desired_size = var.EKS_NODEPOOL["desired"]
    max_size     = var.EKS_NODEPOOL["max"]
    min_size     = var.EKS_NODEPOOL["min"]
  }

  # Set max unavailable in update_config as 1
  update_config {
    max_unavailable = 1
  }

  labels = {
    role = "nodes-general-1"
  }

  depends_on = [
    aws_iam_role_policy_attachment.razorpay-acs-nodes-AmazonEC2ContainerRegistryReadOnly,
    aws_iam_role_policy_attachment.razorpay-acs-nodes-AmazonEKS_CNI_Policy,
    aws_iam_role_policy_attachment.razorpay-acs-nodes-AmazonEKSWorkerNodePolicy
  ]
}



#################################################################################################################

##### Creates private route table for eks data plane and associates with ap-south-1a, ap-south-1b and ap-south-1c
################################################################################################################# 
resource "aws_route_table" "acs-eks-dataplane-routetable" {

  tags = {
    Name = "acs-eks-dataplane-routetable"
  }

  vpc_id = var.RAZORPAY_ACS_VPC_ID

}

##########################################

##@ eks dataplane route for nat g/w ######
##########################################

resource "aws_route" "acs-eks-route-nat1" {
  route_table_id         = aws_route_table.acs-eks-dataplane-routetable.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = var.NAT_GATEWAY_ID
}


#### Route table association for subnet 10.0.4.0/24 ap-south-1a  with nat g.w-1######

resource "aws_route_table_association" "acs-eks-dataplane-1a" {
  subnet_id      = var.EKS_DATPLANE_SUBNET[0]
  route_table_id = aws_route_table.acs-eks-dataplane-routetable.id


}

#### Route table association for subnet 10.0.5.0/24 ap-south-1b with nat g.w-1 ######

resource "aws_route_table_association" "acs-eks-dataplane-1b" {
  subnet_id      = var.EKS_DATPLANE_SUBNET[1]
  route_table_id = aws_route_table.acs-eks-dataplane-routetable.id


}

#### Route table association for subnet 10.0.6.0/24 ap-south-1c with nat g.w-1 ######

resource "aws_route_table_association" "acs-eks-dataplane-1c" {

  subnet_id = var.EKS_DATPLANE_SUBNET[2]


  route_table_id = aws_route_table.acs-eks-dataplane-routetable.id

}


