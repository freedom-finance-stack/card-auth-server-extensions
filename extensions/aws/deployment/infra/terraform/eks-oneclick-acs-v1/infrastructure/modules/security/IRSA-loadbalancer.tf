module "lb_role" {
  source = "terraform-aws-modules/iam/aws//modules/iam-role-for-service-accounts-eks"

  role_name                              = "razorpay-acs-loadbalancer-controller-role"
  attach_load_balancer_controller_policy = true


  oidc_providers = {
    main = {
      provider_arn               = aws_iam_openid_connect_provider.razorpay-eks-oidc.arn
      namespace_service_accounts = ["kube-system:aws-load-balancer-controller"]
    }
  }
}

output "lb-role-arn" {
  value = module.lb_role.iam_role_arn
}


