variable "AWS_ACCESS_KEY" {
  default = "acs-bastion-key"
}

variable "AWS_SECRET_KEY" {
  default = "acs-bastion-key.pub"
}


variable "EKS_REGION" {
  type        = string
  default     = "ap-south-1"
  description = "Region where cluster should be created"
}

variable "BASTION_AMI" {
  type        = map(any)
  description = "AMI id for bastion host, it should be available in target region"
  default = {
    "ap-south-1" = "ami-067c21fb1979f0b27"
    "ap-south-2" = "ami-09a6c8d6711a579cd"
    "us-east-1"  = "ami-067d1e60475437da2"
  }
}

variable "BASTION_INSTANCE_TYPE" {
  type        = string
  default     = "t2.micro"
  description = "bastion instance type"

}




