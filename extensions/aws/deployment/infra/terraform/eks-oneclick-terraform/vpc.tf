
############################################################################

####### VPC created in mumbai region leveraging 3 AZs with totally 9 subnets

###### 3 public subnets

###### 3 private subnets for eks dataplane nodes

###### 3 private subnets for RDS MySQL DB

###### 1 Internet gateway for the VPC

###### 2 Nat Gateways - 1 in ap-south-1a and 1 in ap-south-1b


############################################################################

resource "aws_vpc" "razorpay-acs-vpc" {

  cidr_block = "10.0.0.0/16"

  instance_tenancy = "default"


  tags = {
    Name = "razorpay-acs-vpc"
  }


  # Required for EKS
  enable_dns_support = true

  # Required of EKS setup
  enable_dns_hostnames = true

  # disable classic linkc



  assign_generated_ipv6_cidr_block = false



}



##### creates internet gateway and associates with vpc

resource "aws_internet_gateway" "razorpay-acs-igw" {
  vpc_id = aws_vpc.razorpay-acs-vpc.id

  tags = {
    "Name" : "acs-igw"
  }

}


#### Creates nat gateway and associates with ap-south-1a for eks-dataplane nodes
resource "aws_nat_gateway" "razorpay-acs-natgw1" {
  subnet_id = aws_subnet.publicsubnet-apsouth-1a.id

  tags = {
    "Name" : "acs-natgw-apsouth-1a"

  }

  allocation_id = aws_eip.razorpay-acs-eip.id

}


#### Creates nat gateway and associates with ap-south-1b for database nodes

resource "aws_nat_gateway" "razorpay-acs-natgw2" {
  subnet_id = aws_subnet.publicsubnet-apsouth-1b.id

  tags = {
    "Name" : "acs-natgw-apsouth-1b"

  }

  allocation_id = aws_eip.razorpay-acs-eip-nat2.id

}

###### Creates elastic ip for nat g/w 1
resource "aws_eip" "razorpay-acs-eip" {

  tags = {
    "Name" : "acs-eip-nat1"
  }

  depends_on = [aws_internet_gateway.razorpay-acs-igw]

}


# creates elastic ip for nat g/w 2
resource "aws_eip" "razorpay-acs-eip-nat2" {

  tags = {
    "Name" : "razorpay-acs-eip-nat2"
  }

  depends_on = [aws_internet_gateway.razorpay-acs-igw]
}


##### Outputs the created vpc id
output "vpc_id" {

  value       = aws_vpc.razorpay-acs-vpc.id
  description = "VPC id."

  sensitive = false

}



