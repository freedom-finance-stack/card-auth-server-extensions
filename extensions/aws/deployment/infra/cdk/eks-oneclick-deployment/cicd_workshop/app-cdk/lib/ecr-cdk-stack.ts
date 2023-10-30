import { Stack, StackProps, RemovalPolicy } from "aws-cdk-lib";
import { Construct } from "constructs";
import * as ecr from "aws-cdk-lib/aws-ecr";

export class EcrCdkStack extends Stack {
  public readonly repository: ecr.Repository;

  constructor(scope: Construct, id: string, props: StackProps) {
    super(scope, id, props);
    this.repository = new ecr.Repository(this, "my_ecr", {
      repositoryName: "freedomstack",
      removalPolicy: RemovalPolicy.RETAIN,
    });
    
  }
}
