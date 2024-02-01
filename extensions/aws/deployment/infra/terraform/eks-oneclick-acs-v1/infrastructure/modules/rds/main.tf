resource "aws_db_instance" "acs-mysql-db" {

  engine         = "mysql"
  engine_version = var.MYSQL_VERSION

  multi_az = true

  username = var.MYSQL_USER
  password = var.MYSQL_PWD

  identifier = var.MYSQL_IDENTIFIER

  instance_class = var.MYSQL_INSTANCE_TYPE

  allocated_storage = var.MYSQL_STORAGE

  #db_subnet_group_name = aws_db_subnet_group.datbase_subnet_group.name
  db_subnet_group_name = var.AWS_DB_SUBNET_GROUP_NAME

  vpc_security_group_ids = [aws_security_group.private-ssh.id]


  db_name             = var.MYSQL_DB_NAME
  skip_final_snapshot = true

  #depends_on = [aws_db_subnet_group.datbase_subnet_group]
}

resource "aws_security_group" "private-ssh" {

  #vpc_id = aws_vpc.razorpay-acs-vpc.id
  vpc_id = var.RAZORPAY_ACS_VPC_ID

  name        = "private ssh to rds instance"
  description = "security group for private that allows ssh and all egress traffic"

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]

  }

  ingress {
    from_port       = 22
    to_port         = 22
    protocol        = "tcp"
    security_groups = [var.BASTION_SSH_ID]


  }

  ingress {
    from_port       = 3306
    to_port         = 3306
    protocol        = "tcp"
    security_groups = [var.BASTION_SSH_ID]


  }

  ingress {
    description = "http access"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = var.EKS_PRIVATE_SUBNETS_CIDR

  }


  tags = {
    Name = "private-ssh"
  }

}


############################################################################################
########### Creates a private route table for rds mysql and associates with private route 
########### with the database subnets - 10.0.7.0/24, 10.0.8.0/24 abd 10.0.8.0/24
#############################################################################################


resource "aws_route_table" "acs-database-routetable" {

  tags = {
    Name = "acs-database-routetable"
  }

  vpc_id = var.RAZORPAY_ACS_VPC_ID
}

#### Creates a private route to 0.0.0.0/0 throught nat g/w 2######################################

resource "aws_route" "acs-eks-route-nat2" {
  route_table_id         = aws_route_table.acs-database-routetable.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = var.NAT_GATEWAY_ID
}

#### Route table association for subnet 10.0.7.0/24 ap-south-1a  with nat g.w-2 ##################

resource "aws_route_table_association" "acs-database-1a" {
  subnet_id      = var.DB_SUBNET_0_ID
  route_table_id = aws_route_table.acs-database-routetable.id

}

#### Route table association for subnet 10.0.8.0/24 ap-south-1b  with nat g.w-2 ##################

resource "aws_route_table_association" "acs-database-1b" {
  subnet_id      = var.DB_SUBNET_1_ID
  route_table_id = aws_route_table.acs-database-routetable.id


}

#### Route table association for subnet 10.0.9.0/24 ap-south-1c  with nat g.w-2 ##################

resource "aws_route_table_association" "acs-database-1c" {
  subnet_id      = var.DB_SUBNET_2_ID
  route_table_id = aws_route_table.acs-database-routetable.id

}