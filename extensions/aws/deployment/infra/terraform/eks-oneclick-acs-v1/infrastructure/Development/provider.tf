terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = ">= 2.23.0"
    }
  }
}
provider "aws" {
  region     = var.EKS_REGION
}

variable "PRIVATE_KEY" {
  default = "acs-bastion-key"
}

variable "PUBLIC_KEY" {
  default = "acs-bastion-key.pub"
}