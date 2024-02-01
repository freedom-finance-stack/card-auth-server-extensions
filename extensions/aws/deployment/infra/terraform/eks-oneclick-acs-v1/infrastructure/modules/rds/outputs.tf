##### Outputs the created RDS Endpoint
output "rds_endpoint" {
  value = aws_db_instance.acs-mysql-db.address
}

output "rds_username" {
  value = aws_db_instance.acs-mysql-db.username
}

output "rds_password" {
  value = aws_db_instance.acs-mysql-db.password
}