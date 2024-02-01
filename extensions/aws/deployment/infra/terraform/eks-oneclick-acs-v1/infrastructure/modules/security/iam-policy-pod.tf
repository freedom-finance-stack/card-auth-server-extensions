resource "aws_iam_policy" "razorpay-acs-pod-policy" {
  name = "razorpay-acs-pod-policy"

  policy = jsonencode(
    {
      "Version" : "2012-10-17",
      "Statement" : [
        {
          "Sid" : "SecretsManagerDbCredentialsAccess",
          "Effect" : "Allow",
          "Action" : [
            "secretsmanager:GetSecretValue",
            "secretsmanager:PutResourcePolicy",
            "secretsmanager:PutSecretValue",
            "secretsmanager:DeleteSecret",
            "secretsmanager:DescribeSecret",
            "secretsmanager:TagResource"
          ],
          "Resource" : "arn:aws:secretsmanager:*:*:secret:rds-db-credentials/*"
        },
        {
          "Sid" : "RDSDataServiceAccess",
          "Effect" : "Allow",
          "Action" : [
            "dbqms:CreateFavoriteQuery",
            "dbqms:DescribeFavoriteQueries",
            "dbqms:UpdateFavoriteQuery",
            "dbqms:DeleteFavoriteQueries",
            "dbqms:GetQueryString",
            "dbqms:CreateQueryHistory",
            "dbqms:DescribeQueryHistory",
            "dbqms:UpdateQueryHistory",
            "dbqms:DeleteQueryHistory",
            "rds-data:ExecuteSql",
            "rds-data:ExecuteStatement",
            "rds-data:BatchExecuteStatement",
            "rds-data:BeginTransaction",
            "rds-data:CommitTransaction",
            "rds-data:RollbackTransaction",
            "secretsmanager:CreateSecret",
            "secretsmanager:ListSecrets",
            "secretsmanager:GetRandomPassword",
            "tag:GetResources"
          ],
          "Resource" : "*"
        }
      ]
    }



  )
}

data "aws_iam_policy_document" "razorpay_acs_oidc_assume_role_policy" {
  statement {
    actions = ["sts:AssumeRoleWithWebIdentity"]
    effect  = "Allow"

    condition {
      test     = "StringEquals"
      variable = "${replace(aws_iam_openid_connect_provider.razorpay-eks-oidc.url, "https://", "")}:sub"
      values   = ["system:serviceaccount:default:aws-test"]
    }

    principals {
      identifiers = [aws_iam_openid_connect_provider.razorpay-eks-oidc.arn]
      type        = "Federated"
    }
  }
}

resource "aws_iam_role" "razorpay-acs-oidc-role" {
  assume_role_policy = data.aws_iam_policy_document.razorpay_acs_oidc_assume_role_policy.json
  name               = "razorpay-acs-oidc-role"
}

resource "aws_iam_role_policy_attachment" "razorpay-acs-oidc-role-attachment" {
  role       = aws_iam_role.razorpay-acs-oidc-role.name
  policy_arn = aws_iam_policy.razorpay-acs-pod-policy.arn
}

output "razorpay_acs_oidc_policy_arn" {
  value = aws_iam_role.razorpay-acs-oidc-role.name
}