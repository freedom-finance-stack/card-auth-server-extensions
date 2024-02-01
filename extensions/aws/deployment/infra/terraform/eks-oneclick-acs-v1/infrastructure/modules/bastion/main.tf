
resource "aws_key_pair" "bastion-key-pair" {

  key_name   = "bastion-key-pair"
  public_key = file(var.PUBLIC_KEY)

}

data "aws_ami" "amazon-linux-2" {
  most_recent = true

  filter {
    name   = "owner-alias"
    values = ["amazon"]
  }

  filter {
    name   = "name"
    values = ["al*ami*kernel*-x86_64*"]
  }

  filter {
    name   = "description"
    values = ["Amazon Linux*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }

  filter {
    name   = "architecture"
    values = ["x86_64"]
  }
}

resource "aws_instance" "bastion_host" {
  //ami                    = var.bastion_ami[var.eks_region]
  ami                    = data.aws_ami.amazon-linux-2.id
  instance_type          = var.BASTION_INSTANCE_TYPE
  subnet_id              = var.EKS_PUBLIC_SUBNET[0]
  vpc_security_group_ids = [var.BASTION_SSH_ID]
  key_name               = aws_key_pair.bastion-key-pair.key_name

  user_data = file("${path.module}/bastion-init.sh")

  tags = {
    Name = "acs_bastion_host"
  }
}


##### Outputs the created eks cluster name id and name
output "bastion_ami_id" {

  value       = data.aws_ami.amazon-linux-2
  description = "bastion ami id"
  sensitive   = false

}

