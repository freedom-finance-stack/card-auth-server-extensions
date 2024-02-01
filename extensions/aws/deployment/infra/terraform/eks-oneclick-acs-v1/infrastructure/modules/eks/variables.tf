variable "EKS_WORKER_INSTANCE_TYPE" {
  type        = list(string)
  default     = ["t3.medium"]
  description = "eks worker node instance type"
}

variable "EKS_CLUSTER_NAME" {
  default     = "razorpay-acs-eks-cluster"
  type        = string
  description = "RazorPay ACS EKS Cluster"
  nullable    = false
}
variable "EKS_DATPLANE_SUBNET" {
  type        = list(string)
}
variable "EKS_PUBLIC_SUBNET" {
  type        = list(string)
}
variable "RAZORPAY_ACS_VPC_ID" {
  type = string
}
variable "NAT_GATEWAY_ID" {
  type = string
}

variable "EKS_NODEPOOL" {
  type        = map(any)
  description = "eks noodpool min, max, desired count"
  default = {
    "min"     = 2
    "max"     = 5
    "desired" = 3
  }
}