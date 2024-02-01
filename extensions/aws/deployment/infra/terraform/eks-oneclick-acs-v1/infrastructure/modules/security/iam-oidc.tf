data "tls_certificate" "eks" {
  url = aws_eks_cluster.razorpay-acs-eks-cluster.identity[0].oidc[0].issuer

}

resource "aws_iam_openid_connect_provider" "razorpay-eks-oidc" {
  client_id_list  = ["sts.amazonaws.com"]
  thumbprint_list = [data.tls_certificate.eks.certificates[0].sha1_fingerprint]
  url             = aws_eks_cluster.razorpay-acs-eks-cluster.identity[0].oidc[0].issuer
}

output "eks_tls_certificate" {
  value = aws_eks_cluster.razorpay-acs-eks-cluster.identity[0].oidc[0].issuer
}

output "aws_iam_openid_connect_provider" {
  value = aws_iam_openid_connect_provider.razorpay-eks-oidc.arn
}


output "cluster-ca-cert" {
  value = aws_eks_cluster.razorpay-acs-eks-cluster.certificate_authority[0].data

}

