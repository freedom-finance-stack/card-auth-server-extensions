#################################################################################################################

##### Creates private route table for eks data plane and associates with ap-south-1a, ap-south-1b and ap-south-1c
################################################################################################################# 
resource "aws_route_table" "acs-eks-dataplane-routetable" {

  tags = {
    Name = "acs-eks-dataplane-routetable"
  }

  vpc_id = aws_vpc.razorpay-acs-vpc.id



}

##########################################

##@ eks dataplane route for nat g/w ######
##########################################

resource "aws_route" "acs-eks-route-nat1" {
  route_table_id         = aws_route_table.acs-eks-dataplane-routetable.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_nat_gateway.razorpay-acs-natgw1.id
}


#### Route table association for subnet 10.0.4.0/24 ap-south-1a  with nat g.w-1######

resource "aws_route_table_association" "acs-eks-dataplane-1a" {
  subnet_id      = aws_subnet.acs-dataplane-subnet-1a.id
  route_table_id = aws_route_table.acs-eks-dataplane-routetable.id


}

#### Route table association for subnet 10.0.5.0/24 ap-south-1b with nat g.w-1 ######

resource "aws_route_table_association" "acs-eks-dataplane-1b" {
  subnet_id      = aws_subnet.acs-dataplane-subnet-1b.id
  route_table_id = aws_route_table.acs-eks-dataplane-routetable.id


}

#### Route table association for subnet 10.0.6.0/24 ap-south-1c with nat g.w-1 ######

resource "aws_route_table_association" "acs-eks-dataplane-1c" {

  subnet_id = aws_subnet.acs-dataplane-subnet-1c.id


  route_table_id = aws_route_table.acs-eks-dataplane-routetable.id


}