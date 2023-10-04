
###############################################################################################################

###### creates 3 public subnets for public traffic - ap-south-1a, ap-south-1b, ap-south-1c - public traffic
###### creates 3 private subnets for public traffic - ap-south-1a, ap-south-1b, ap-south-1c - eks dataplane nodes
###### creates 3 private subnets for public traffic - ap-south-1a, ap-south-1b, ap-south-1c - rds mysql database

##################################################################################################################


####### public subnet - ap-south-1a 

resource "aws_subnet" "publicsubnet-apsouth-1a" {
  vpc_id = aws_vpc.razorpay-acs-vpc.id

  cidr_block = "10.0.1.0/24"
  tags = {
    Name                        = "acs-public-subnet-1a"
    "kubernetes.io/cluster/eks" = "shared"
    "kubernetes.io.role/elb"    = 1
  }

  #availability zone
  availability_zone = "ap-south-1a"

  # Required for EKS
  map_public_ip_on_launch = true


}

####### public subnet - ap-south-1b

resource "aws_subnet" "publicsubnet-apsouth-1b" {
  vpc_id = aws_vpc.razorpay-acs-vpc.id

  cidr_block = "10.0.2.0/24"

  tags = {
    Name                        = "acs-public-subnet-1b"
    "kubernetes.io/cluster/eks" = "shared"
    "kubernetes.io.role/elb"    = 1
  }

  #availability zone
  availability_zone = "ap-south-1b"

  # Required for EKS
  map_public_ip_on_launch = true
}

####### public subnet - ap-south-1c

resource "aws_subnet" "publicsubnet-apsouth-1c" {
  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  cidr_block = "10.0.3.0/24"

  tags = {
    Name                        = "acs-public-subnet-1c"
    "kubernetes.io/cluster/eks" = "shared"
    "kubernetes.io.role/elb"    = 1
  }

  #availability zone
  availability_zone = "ap-south-1c"

  # Required for EKS
  map_public_ip_on_launch = true



}

####### private subnet - ap-south-1a - eks data plane

resource "aws_subnet" "acs-dataplane-subnet-1a" {
  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  cidr_block = "10.0.4.0/24"

  tags = {
    Name                              = "acs-dataplane-subnet-1a"
    Tier                              = "App"
    "kubernetes.io/cluster/eks"       = "shared"
    "kubernetes.io.role/internal-elb" = 1
  }

  availability_zone = "ap-south-1a"


}

####### private subnet - ap-south-1b - eks data plane
resource "aws_subnet" "acs-dataplane-subnet-1b" {
  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  cidr_block = "10.0.5.0/24"

  tags = {
    Name                              = "acs-dataplane-subnet-1b"
    Tier                              = "App"
    "kubernetes.io/cluster/eks"       = "shared"
    "kubernetes.io.role/internal-elb" = 1
  }

  availability_zone = "ap-south-1b"


}

####### private subnet - ap-south-1c - eks data plane
resource "aws_subnet" "acs-dataplane-subnet-1c" {


  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  cidr_block = "10.0.6.0/24"

  tags = {
    Name                              = "acs-dataplane-subnet-1c"
    Tier                              = "App"
    "kubernetes.io/cluster/eks"       = "shared"
    "kubernetes.io.role/internal-elb" = 1
  }

  availability_zone = "ap-south-1c"


}

####### private subnet - ap-south-1a - database subnets
resource "aws_subnet" "acs-database-subnet-1a" {


  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  cidr_block = "10.0.7.0/24"

  tags = {
    Name = "acs-database-subnet-1a"
  }
  availability_zone = "ap-south-1a"


}

####### private subnet - ap-south-1b - database subnets
resource "aws_subnet" "acs-database-subnet-1b" {


  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  cidr_block = "10.0.8.0/24"

  tags = {
    Name = "acs-database-subnet-1b"
  }
  availability_zone = "ap-south-1b"


}

####### private subnet - ap-south-1c - database subnets
resource "aws_subnet" "acs-database-subnet-1c" {


  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  cidr_block = "10.0.9.0/24"

  tags = {
    Name = "acs-database-subnet-1c"
  }

  availability_zone = "ap-south-1c"

}



resource "aws_db_subnet_group" "datbase_subnet_group" {
  name = "acs-database-subnets"

  subnet_ids = [aws_subnet.acs-database-subnet-1a.id, aws_subnet.acs-database-subnet-1b.id, aws_subnet.acs-database-subnet-1c.id]

  description = "subnets for database instance"

  tags = {
    Name = "acs-database-subnets"
  }


}

