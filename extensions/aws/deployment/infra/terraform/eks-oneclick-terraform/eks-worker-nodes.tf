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

  # The following lines will place worker nodes in the private subnets in mumbai region such as 
  # ap-south-1a : 10.0.4.0/24
  # ap-south-1b : 10.0.5.0/24
  # ap-south-1c : 10.0.6.0/24

  subnet_ids = [
    aws_subnet.acs-dataplane-subnet-1a.id,
    aws_subnet.acs-dataplane-subnet-1b.id,
    aws_subnet.acs-dataplane-subnet-1c.id

  ]

  # Selecting t3.medium
  capacity_type  = "ON_DEMAND"
  instance_types = ["t3.medium"]


  # disk size
  disk_size = 40


  force_update_version = false

  # Define the scale config
  scaling_config {
    desired_size = 3
    max_size     = 5
    min_size     = 2
  }

  # Set max unavailable in update_config as 1
  update_config {
    max_unavailable = 1
  }

  labels = {
    role = "nodes-general"
  }

  depends_on = [
    aws_iam_role_policy_attachment.razorpay-acs-nodes-AmazonEC2ContainerRegistryReadOnly,
    aws_iam_role_policy_attachment.razorpay-acs-nodes-AmazonEKS_CNI_Policy,
    aws_iam_role_policy_attachment.razorpay-acs-nodes-AmazonEKSWorkerNodePolicy


  ]


}

