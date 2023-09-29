



#Step 1 - The following IAM role will be attached to EKS cluster during its creation.check "name" 
resource "aws_iam_role" "razorpay-acs-eks-cluster-role" {
  name = "razorpay-acs-eks-cluster-role"

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


#Step 3 – Add variable name for cluster

variable "eks-cluster-name" {
  default     = "razorpay-acs-eks-cluster"
  type        = string
  description = "RazorPay ACS EKS Cluster"
  nullable    = false
}

#Start creating the cluster
resource "aws_eks_cluster" "razorpay-acs-eks-cluster" {


  name = var.eks-cluster-name

  role_arn = aws_iam_role.razorpay-acs-eks-cluster-role.arn

  enabled_cluster_log_types = ["api", "audit"]






  vpc_config {

    endpoint_private_access = false
    endpoint_public_access  = true

    subnet_ids = [
      aws_subnet.acs-dataplane-subnet-1a.id,
      aws_subnet.acs-dataplane-subnet-1b.id,
      aws_subnet.acs-dataplane-subnet-1c.id,
      aws_subnet.publicsubnet-apsouth-1a.id,
      aws_subnet.publicsubnet-apsouth-1b.id,
      aws_subnet.publicsubnet-apsouth-1c.id,


    ]




  }
  depends_on = [aws_iam_role_policy_attachment.razorpay-ecs-AmazonEKSClusterPolicy]
}


##### Outputs the created eks cluster name id and name
output "eks_cluster_name" {

  value       = aws_eks_cluster.razorpay-acs-eks-cluster.name
  description = "EKS Cluster Name"

  sensitive = false

}