
resource "kubernetes_ingress_v1" "ingress" {
  wait_for_load_balancer = true
  metadata {
    name = "simple-fanout-ingress"
  }

  spec {
    ingress_class_name = "nginx"

    rule {
      http {
        path {
          backend {
            service {
              name = "acs-service"
              port {
                number = 5678
              }
            }
          }

          path = "/acs"
        }

      }
    }

  }
}

#############################################
resource "kubernetes_pod_v1" "cas_acs" {
  metadata {
    name = "acs"
    labels = {
      "app" = "cas_acs"
    }
  }

  spec {
    container {
      image = "razorpay/freedomfinancestack:8ddcbd65a8ca01ee3d750699ad94b8ed43454fa5"
      name  = "cas-acs-server"
      env {
        name  = "environment"
        value = "--ACS_MYSQL_PORT=${var.RDS_ENDPOINT} --ACS_MYSQL_DATABASE=acsdb --ACS_MYSQL_USER=${var.RDS_USER} --ACS_MYSQL_PASSWORD=${var.RDS_PWD}"
      }

      port {
        container_port = 8080
      }
      security_context {
        allow_privilege_escalation = false
        privileged = false
        run_as_non_root = true
         seccomp_profile {
          type = "RuntimeDefault"
        }
        capabilities {
          drop = ["ALL"]
        }
        run_as_user = 9999
      }
      
    }
  }
}


#############################################
resource "kubernetes_service_v1" "cas_acs_service" {
  metadata {
    name = "cas-acs-service"
  }
  spec {
    selector = {
      app = kubernetes_pod_v1.cas_acs.metadata.0.labels.app
    }
    port {
      port = 5678
      target_port = 8080
    }
  }
}

