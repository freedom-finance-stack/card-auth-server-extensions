
############################################################################
####### VPC created in mumbai region leveraging 3 AZs with totally 9 subnets
###### 3 public subnets
###### 3 private subnets for eks dataplane nodes
###### 3 private subnets for RDS MySQL DB
###### 1 Internet gateway for the VPC
###### 2 Nat Gateways - 1 in 1a and 1 in 1b
############################################################################


resource "aws_vpc" "razorpay-acs-vpc" {
  cidr_block       = "10.0.0.0/16"
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

##### associates a secondary cidr block with the vpc
resource "aws_vpc_ipv4_cidr_block_association" "secondary_cidr" {
  vpc_id           = aws_vpc.razorpay-acs-vpc.id
  cidr_block       = "10.1.0.0/16" # Secondary CIDR block for the VPC
}

##### creates internet gateway and associates with vpc
resource "aws_internet_gateway" "razorpay-acs-igw" {
  vpc_id = aws_vpc.razorpay-acs-vpc.id
  tags = {
    "Name" : "acs-igw"
  }
}

#### Creates nat gateway and associates with 1a for eks-dataplane nodes
resource "aws_nat_gateway" "razorpay-acs-natgw1" {
  subnet_id = aws_subnet.eks-publicsubnet[0].id
  tags = {
    "Name" : "acs-natgw-1a"
  }
  allocation_id = aws_eip.razorpay-acs-eip.id
}

#### Creates nat gateway and associates with 1b for database nodes
resource "aws_nat_gateway" "razorpay-acs-natgw2" {
  subnet_id = aws_subnet.eks-publicsubnet[1].id
  tags = {
    "Name" : "acs-natgw-1b"
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



###############################################################################################################

###### creates 3 public subnets for public traffic - 1a, 1b, 1c - public traffic
###### creates 3 private subnets for public traffic - 1a, 1b, 1c - eks dataplane nodes
###### creates 3 private subnets for public traffic - 1a, 1b, 1c - rds mysql database

##################################################################################################################

data "aws_availability_zones" "available" {
  state = "available"
}

####### public subnets
resource "aws_subnet" "eks-publicsubnet" {
  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  count      = length(data.aws_availability_zones.available.names) <= 3 ? length(data.aws_availability_zones.available.names) : 3
  cidr_block = var.EKS_PUBLIC_SUBNETS_CIDR[count.index]
  tags = {
    Name                                             = "acs-public-subnet-${data.aws_availability_zones.available.names[count.index]}"
    "kubernetes.io/cluster/razorpay-acs-eks-cluster" = "owned"
    "kubernetes.io/role/elb"                         = "1"
  }
  availability_zone = data.aws_availability_zones.available.names[count.index]
  # Required for EKS
  map_public_ip_on_launch = true
  # Secondary CIDR association is needed for subnet masking
  depends_on = [aws_vpc_ipv4_cidr_block_association.secondary_cidr]
}

####### private subnet - eks data plane
resource "aws_subnet" "eks-dataplane-subnet" {
  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  count      = length(data.aws_availability_zones.available.names) <= 3 ? length(data.aws_availability_zones.available.names) : 3
  cidr_block = var.EKS_PRIVATE_SUBNETS_CIDR[count.index]
  tags = {
    Name                                             = "acs-dataplane-subnet-${data.aws_availability_zones.available.names[count.index]}"
    Tier                                             = "App"
    "kubernetes.io/cluster/razorpay-acs-eks-cluster" = "owned"
    "kubernetes.io/role/internal-elb"                = "1"
  }
  availability_zone = data.aws_availability_zones.available.names[count.index]
  # Secondary CIDR association is needed for subnet masking
  depends_on = [aws_vpc_ipv4_cidr_block_association.secondary_cidr]
}

####### private subnet - acs database
resource "aws_subnet" "acs-database-subnet" {
  vpc_id     = aws_vpc.razorpay-acs-vpc.id
  count      = length(data.aws_availability_zones.available.names) <= 3 ? length(data.aws_availability_zones.available.names) : 3
  cidr_block = var.RDS_PRIVATE_SUBNETS_CIDR[count.index]

  tags = {
    Name = "acs-database-subnet-${data.aws_availability_zones.available.names[count.index]}"
    Tier = "DB"
  }
  availability_zone = data.aws_availability_zones.available.names[count.index]
  # Secondary CIDR association is needed for subnet masking
  depends_on = [aws_vpc_ipv4_cidr_block_association.secondary_cidr]
}


resource "aws_db_subnet_group" "datbase_subnet_group" {
  name        = "acs-database-subnets"
  subnet_ids  = [aws_subnet.acs-database-subnet[0].id, aws_subnet.acs-database-subnet[1].id, aws_subnet.acs-database-subnet[2].id]
  description = "subnets for database instance"
  tags = {
    Name = "acs-database-subnets"
  }
}


#######################################################################################

##### Creates public route table with traffic out (0.0.0.0/0) to interet gateway
########################################################################################


##### public route table creation
resource "aws_route_table" "acs-public-routetable" {

  tags = {
    Name = "acs-public-routetable"
  }

  vpc_id = aws_vpc.razorpay-acs-vpc.id



}

##### public route allowing traffic (0.0.0.0/0) out to internet gateway - 10.0.1.0/24

resource "aws_route" "acs-public-route" {
  route_table_id         = aws_route_table.acs-public-routetable.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.razorpay-acs-igw.id
}

##### associating public route to the public subnet 1a - 10.0.2.0/24
resource "aws_route_table_association" "acs-public-routetable-association-1a" {
  subnet_id      = aws_subnet.eks-publicsubnet[0].id
  route_table_id = aws_route_table.acs-public-routetable.id


}

##### associating public route to the public subnet 1b - 10.0.3.0/24
resource "aws_route_table_association" "acs-public-routetable-association-1b" {
  subnet_id      = aws_subnet.eks-publicsubnet[1].id
  route_table_id = aws_route_table.acs-public-routetable.id


}

##### associating public route to the public subnet 1c
resource "aws_route_table_association" "acs-public-routetable-association-1c" {
  subnet_id      = aws_subnet.eks-publicsubnet[2].id
  route_table_id = aws_route_table.acs-public-routetable.id


}

## Bastion host security group
resource "aws_security_group" "bastion-allow-ssh" {

  vpc_id = aws_vpc.razorpay-acs-vpc.id
  name   = "bastion-allow-ssh"

  description = "security group for bastion that allows ssh and all egress traffic"

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]


  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }


  tags = {
    Name = "bastion-allow-ssh"
  }

}

