terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

# Configure the AWS Provider
provider "aws" {
  region = "ap-south-1"

}

variable "PRIVATE_KEY" {
  default = "acs-bastion-key"

}

variable "PUBLIC_KEY" {

  default = "acs-bastion-key.pub"
}

