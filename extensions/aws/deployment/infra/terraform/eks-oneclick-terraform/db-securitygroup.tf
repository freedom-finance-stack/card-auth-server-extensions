resource "aws_security_group" "private-ssh" {

  vpc_id = aws_vpc.razorpay-acs-vpc.id

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
    security_groups = [aws_security_group.bastion-allow-ssh.id]


  }

  ingress {
    from_port       = 3306
    to_port         = 3306
    protocol        = "tcp"
    security_groups = [aws_security_group.bastion-allow-ssh.id]


  }

  ingress {
    description = "http access"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["10.0.4.0/24", "10.0.5.0/24", "10.0.6.0/24"]

  }


  tags = {
    Name = "private-ssh"
  }

}

