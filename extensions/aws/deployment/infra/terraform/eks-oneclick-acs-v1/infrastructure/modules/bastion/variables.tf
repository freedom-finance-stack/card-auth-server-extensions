variable "PRIVATE_KEY" {
  default = "acs-bastion-key"
}

variable "PUBLIC_KEY" {
  default = "acs-bastion-key.pub"
}

variable "BASTION_INSTANCE_TYPE" {
  type        = string
  default     = "t2.micro"
  description = "bastion instance type"

}

variable "EKS_PUBLIC_SUBNET" {
  type        = list(string)
}
variable "BASTION_SSH_ID" {
  type = string
}