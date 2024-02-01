

variable "VPC_CIDR" {
  type        = string
  default     = "10.0.0.0/16"
  description = "CIDR for VPC"
}

variable "EKS_PUBLIC_SUBNETS_CIDR" {
  type        = list(string)
  default     = ["10.0.1.0/24", "10.0.2.0/24", "10.1.1.0/24"]
  description = "CIDR for public subnets"
}

variable "EKS_PRIVATE_SUBNETS_CIDR" {
  type        = list(string)
  default     = ["10.0.3.0/24", "10.0.4.0/24", "10.1.2.0/24"]
  description = "CIDR for public subnets"
}

variable "RDS_PRIVATE_SUBNETS_CIDR" {
  type        = list(string)
  default     = ["10.0.5.0/24", "10.0.8.0/24", "10.1.3.0/24"]
  description = "CIDR for public subnets"
}