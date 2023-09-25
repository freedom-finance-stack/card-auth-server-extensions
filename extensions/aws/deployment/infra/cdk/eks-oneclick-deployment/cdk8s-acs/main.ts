import { Construct } from "constructs";
import { App, Chart, ChartProps } from "cdk8s";

import {
  KubeDeployment,
  KubeService,
  IntOrString,
  KubeSecret
} from "./imports/k8s";

export class ACSChart extends Chart {
  constructor(scope: Construct, id: string, props: ChartProps = {}) {
    super(scope, id, props);

    const appLabelACS = { app: "cas-acs-server" };

    new KubeDeployment(this, "deployment", {
      spec: {
        replicas: 2,
        selector: {
          matchLabels: appLabelACS,
        },
        template: {
          metadata: {
            labels: appLabelACS,
          },
          spec: {
            containers: [
              {
                name: "cas-acs-server",
                image:
                  "412681425637.dkr.ecr.us-west-2.amazonaws.com/card-auth-server:latest",
                ports: [{ containerPort: 8080 }],
                env: [
                  {
                    name: "ACS_MYSQL_DATABASE",
                    valueFrom: {
                      secretKeyRef: {
                        key: "dbname",
                        name: "acs-secret",
                      },
                    },
                  },
                  {
                    name: "ACS_MYSQL_HOST",
                    valueFrom: {
                      secretKeyRef: {
                        key: "dbhostname",
                        name: "acs-secret",
                      },
                    },
                  },
                  {
                    name: "ACS_MYSQL_PASSWORD",
                    valueFrom: {
                      secretKeyRef: {
                        key: "dbpassword",
                        name: "acs-secret",
                      },
                    },
                  },
                  {
                    name: "ACS_MYSQL_PORT",
                    valueFrom: {
                      secretKeyRef: {
                        key: "dbport",
                        name: "acs-secret",
                      },
                    },
                  },
                  {
                    name: "ACS_MYSQL_USER",
                    valueFrom: {
                      secretKeyRef: {
                        key: "dbusername",
                        name: "acs-secret",
                      },
                    },
                  },
                  {
                    name: "SPRING_PROFILES_ACTIVE",
                    valueFrom: {
                      secretKeyRef: {
                        key: "springprofile",
                        name: "acs-secret",
                      },
                    },
                  },
                ],
              },
            ],
          },
        },
      },
    });

    new KubeService(this, "Service", {
      spec: {
        type: "LoadBalancer",
        ports: [{ port: 8080, targetPort: IntOrString.fromNumber(8080) }],
        selector: appLabelACS,
      },
    });

    new KubeSecret(this, "Secret", {
      metadata: {
        name: "acs-secret",
      },
      type: "Opaque",
      data: {
        dbname: "Y2FzX2RiCg==",
        dbhostname:
          "cmRzLWFjcy5jaTdiYm5ycWZqZWkudXMtd2VzdC0yLnJkcy5hbWF6b25hd3MuY29tCg==",
        dbpassword: "UGFzc3dvcmQxCg==",
        dbport: "MzMwNgo=",
        dbusername: "YWRtaW4K",
        springprofile: "ZGV2Cg==",
      },
    });
  }
}

const app = new App();
new ACSChart(app, "ACS");
app.synth();
