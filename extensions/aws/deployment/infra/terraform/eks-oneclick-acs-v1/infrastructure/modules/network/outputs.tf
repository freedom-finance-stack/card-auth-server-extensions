##### Outputs the created vpc id
output "vpc_id" {
  value       = aws_vpc.razorpay-acs-vpc.id
  description = "VPC id."
  sensitive   = false
}
output "db_subnet_group_name" {
    value = aws_db_subnet_group.datbase_subnet_group.name
    description = "DB Subnet Group Name"
}

output "gateway_id" {
  value       = aws_nat_gateway.razorpay-acs-natgw2.id
  description = "VPC id."
}
output "dbsubnet_0_id" {
  value       = aws_subnet.acs-database-subnet[0].id
  description = "DB Subnet 0"
}
output "dbsubnet_1_id" {
  value       = aws_subnet.acs-database-subnet[1].id
  description = "DB Subnet 1"
}
output "dbsubnet_2_id" {
  value       = aws_subnet.acs-database-subnet[2].id
  description = "DB Subnet 2"
}
output "bastion_ssh_id" {
  value       = aws_security_group.bastion-allow-ssh.id
  description = "DB Subnet 2"
}

output "eks_dataplane_subet" {
  value = aws_subnet.eks-dataplane-subnet[*].id
  description = "EKS dataplace subnet"
}

output "eks_public_subet" {
  value = aws_subnet.eks-publicsubnet[*].id
  description = "EKS dataplane subnet"
}

output "db_private_subet" {
  value = aws_subnet.acs-database-subnet[*].id
  description = "DB Private subnet"
}