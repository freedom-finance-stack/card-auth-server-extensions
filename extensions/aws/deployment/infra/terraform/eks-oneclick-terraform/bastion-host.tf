
resource "aws_key_pair" "bastion-key-pair" {

  key_name   = "bastion-key-pair"
  public_key = file(var.PUBLIC_KEY)

}

resource "aws_instance" "bastion_host" {
  ami                    = "ami-067c21fb1979f0b27"
  instance_type          = "t2.micro"
  subnet_id              = aws_subnet.publicsubnet-apsouth-1a.id
  vpc_security_group_ids = [aws_security_group.bastion-allow-ssh.id]
  key_name               = aws_key_pair.bastion-key-pair.key_name

  user_data = file("bastion-init.sh")

  tags = {
    Name = "acs_bastion_host"
  }

}

