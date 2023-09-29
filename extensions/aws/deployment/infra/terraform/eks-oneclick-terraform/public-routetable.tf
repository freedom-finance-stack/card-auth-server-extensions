
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

##### associating public route to the public subnet ap-south-1a - 10.0.2.0/24
resource "aws_route_table_association" "acs-public-routetable-association-1a" {
  subnet_id      = aws_subnet.publicsubnet-apsouth-1a.id
  route_table_id = aws_route_table.acs-public-routetable.id


}

##### associating public route to the public subnet ap-south-1b - 10.0.3.0/24
resource "aws_route_table_association" "acs-public-routetable-association-1b" {
  subnet_id      = aws_subnet.publicsubnet-apsouth-1b.id
  route_table_id = aws_route_table.acs-public-routetable.id


}

##### associating public route to the public subnet ap-south-1c
resource "aws_route_table_association" "acs-public-routetable-association-1c" {
  subnet_id      = aws_subnet.publicsubnet-apsouth-1c.id
  route_table_id = aws_route_table.acs-public-routetable.id


}



