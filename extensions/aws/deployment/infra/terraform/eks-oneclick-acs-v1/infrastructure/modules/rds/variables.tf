variable "MYSQL_VERSION" {
  default     = "8.0.33"
  type        = string
  description = "MySql version"
  nullable    = false
}

variable "MYSQL_USER" {
  default     = "acsadmin"
  type        = string
  description = "MySql user"
  nullable    = false
}

variable "MYSQL_PWD" {
  default     = "RazorAcs!1234"
  type        = string
  description = "MySql password"
  nullable    = false
}

variable "MYSQL_IDENTIFIER" {
  default     = "razorpay-acs-db-instance"
  type        = string
  description = "MySql Instance Identifier"
  nullable    = false
}

variable "MYSQL_INSTANCE_TYPE" {
  default     = "db.m5d.large"
  type        = string
  description = "MySql Instance Type"
  nullable    = false
}

variable "MYSQL_STORAGE" {
  default     = 75
  type        = number
  description = "MySql Instance Type"
  nullable    = false
}

variable "MYSQL_DB_NAME" {
  default     = "acsdb"
  type        = string
  description = "MySql database name"
  nullable    = false
}
variable "EKS_PRIVATE_SUBNETS_CIDR" {
  type        = list(string)
  default     = ["10.0.3.0/24", "10.0.4.0/24", "10.1.2.0/24"]
  description = "CIDR for public subnets"
}

variable "RAZORPAY_ACS_VPC_ID" {
  type = string
}

variable "AWS_DB_SUBNET_GROUP_NAME" {
  type = string
}

variable "NAT_GATEWAY_ID" {
  type = string
}
variable "DB_SUBNET_0_ID" {
  type = string
}
variable "DB_SUBNET_1_ID" {
  type = string
}
variable "DB_SUBNET_2_ID" {
  type = string
}
variable "BASTION_SSH_ID" {
  type = string
}