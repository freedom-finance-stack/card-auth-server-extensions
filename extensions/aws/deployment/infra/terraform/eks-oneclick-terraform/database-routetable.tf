
############################################################################################
########### Creates a private route table for rds mysql and associates with private route 
########### with the database subnets - 10.0.7.0/24, 10.0.8.0/24 abd 10.0.8.0/24
#############################################################################################


resource "aws_route_table" "acs-database-routetable" {

  tags = {
    Name = "acs-database-routetable"
  }

  vpc_id = aws_vpc.razorpay-acs-vpc.id



}

#### Creates a private route to 0.0.0.0/0 throught nat g/w 2######################################

resource "aws_route" "acs-eks-route-nat2" {
  route_table_id         = aws_route_table.acs-database-routetable.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_nat_gateway.razorpay-acs-natgw2.id
}

#### Route table association for subnet 10.0.7.0/24 ap-south-1a  with nat g.w-2 ##################

resource "aws_route_table_association" "acs-database-1a" {
  subnet_id      = aws_subnet.acs-database-subnet-1a.id
  route_table_id = aws_route_table.acs-database-routetable.id



}

#### Route table association for subnet 10.0.8.0/24 ap-south-1b  with nat g.w-2 ##################

resource "aws_route_table_association" "acs-database-1b" {
  subnet_id      = aws_subnet.acs-database-subnet-1b.id
  route_table_id = aws_route_table.acs-database-routetable.id


}

#### Route table association for subnet 10.0.9.0/24 ap-south-1c  with nat g.w-2 ##################

resource "aws_route_table_association" "acs-database-1c" {
  subnet_id      = aws_subnet.acs-database-subnet-1c.id
  route_table_id = aws_route_table.acs-database-routetable.id


}