import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import * as blueprints from '@aws-quickstart/eks-blueprints';
import { AwsSolutionsChecks, PCIDSS321Checks } from 'cdk-nag'
import { Aspects } from 'aws-cdk-lib';

const app = new cdk.App();
const account = '412681425637';
const region = 'us-east-1';

const addOns: Array<blueprints.ClusterAddOn> = [
    new blueprints.addons.ArgoCDAddOn(),
    new blueprints.addons.CalicoOperatorAddOn(),
    new blueprints.addons.MetricsServerAddOn(),
    new blueprints.addons.ClusterAutoScalerAddOn(),
    new blueprints.addons.AwsLoadBalancerControllerAddOn(),
    new blueprints.addons.VpcCniAddOn(),
    new blueprints.addons.CoreDnsAddOn(),
    new blueprints.addons.KubeProxyAddOn()
];


//Aspects.of(app).add(new AwsSolutionsChecks({ verbose: true }))
//Aspects.of(app).add(new PCIDSS321Checks({ verbose: true }))


//PCIDSS321Checks

const stack = blueprints.EksBlueprint.builder()
    .account(account)
    .region(region)
    .addOns(...addOns)
    .useDefaultSecretEncryption(true) // set to false to turn secret encryption off (non-production/demo cases)
    .build(app, 'eks-blueprint');
    
    
    
    



