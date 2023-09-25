#!/usr/bin/env node
import "source-map-support/register";
import * as cdk from "aws-cdk-lib";
import { AppCdkStack } from "../lib/app-cdk-stack";
import { PipelineCdkStack } from "../lib/pipeline-cdk-stack";
import { EcrCdkStack } from "../lib/ecr-cdk-stack";
import { AwsSolutionsChecks, PCIDSS321Checks } from 'cdk-nag'
import { Aspects } from 'aws-cdk-lib';


const app = new cdk.App();

//Aspects.of(app).add(new AwsSolutionsChecks({ verbose: true }))
//Aspects.of(app).add(new PCIDSS321Checks({ verbose: true }))

const ecrCdkStack = new EcrCdkStack(app, "ecr-stack", {});

new AppCdkStack(app, "AppCdkStack", {});

const pipelineCdkStack = new PipelineCdkStack(app, "pipeline-stack", {
  ecrRepository: ecrCdkStack.repository,
});
