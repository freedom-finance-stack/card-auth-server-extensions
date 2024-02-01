##### Outputs the created eks cluster name id and name
output "eks_cluster_name" {

  value       = aws_eks_cluster.razorpay-acs-eks-cluster.name
  description = "EKS Cluster Name"

  sensitive = false
}

output "eks_cluster_endpoint" {

  value       = aws_eks_cluster.razorpay-acs-eks-cluster.endpoint
  description = "EKS Cluster Endpoint"

  sensitive = false
}

output "eks_cluster_certificates" {

  value       = aws_eks_cluster.razorpay-acs-eks-cluster.certificate_authority[0].data
  description = "EKS Cluster Endpoint"

  sensitive = false
}