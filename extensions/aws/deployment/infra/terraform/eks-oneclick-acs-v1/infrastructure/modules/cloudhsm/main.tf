
resource "aws_cloudhsm_v2_cluster" "razorpay_acs_hsm_cluster" {
  hsm_type   = "hsm1.medium"
  subnet_ids = var.DB_PRIVATE_SUBNET

  tags = {
    Name = "razorpay ACS HSM"
  }
}

# data "aws_cloudhsm_v2_cluster" "cluster" {
#   cluster_id = aws_cloudhsm_v2_cluster.razorpay_acs_hsm_cluster.cluster_id
# }

resource "aws_cloudhsm_v2_hsm" "razorpay_acs_hsm" {
  # cluster_id        = aws_cloudhsm_v2_cluster.razorpay_acs_hsm_cluster.cluster_id
  # subnet_id         = aws_cloudhsm_v2_cluster.razorpay_acs_hsm_cluster.subnet_ids[1]
  subnet_id  = var.HSM_SUBNET_ID
  cluster_id = aws_cloudhsm_v2_cluster.razorpay_acs_hsm_cluster.cluster_id
}