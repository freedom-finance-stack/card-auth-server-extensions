resource "aws_db_instance" "acs-mysql-db" {

  engine         = "mysql"
  engine_version = "8.0.33"

  multi_az = true

  username = "acsadmin"
  password = "RazorAcs!1234"

  identifier = "razorpay-acs-db-instance"

  instance_class = "db.m5d.large"

  allocated_storage = 75

  db_subnet_group_name = aws_db_subnet_group.datbase_subnet_group.name

  vpc_security_group_ids = [aws_security_group.private-ssh.id]


  db_name             = "acsdb"
  skip_final_snapshot = true
}

##### Outputs the created RDS Endpoint
output "rds_endpoint" {
  value = aws_db_instance.acs-mysql-db.address
}

output "rds_username" {
  value = aws_db_instance.acs-mysql-db.username
}