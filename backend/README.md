# 🛡️ Web3Company Vault Service (Spring Boot + Azure CI/CD + Kubernetes)

A minimal secure microservice prototype for handling collateral actions. Fully CI/CD-integrated with security and quality gates — built, scanned, and deployed to a live Kubernetes cluster via Azure DevOps and Azure Kubernetes Service.

> Last Updated: March 24, 2025
---

## 🔨 Tech Stack

| Layer         | Technology                         |
|---------------|-------------------------------------|
| Backend       | Java 17, Spring Boot 3.2, Gradle 8.13 |
| Infra         | Azure Kubernetes Service (AKS), Azure Container Registry (ACR) |
| CI/CD         | Azure Pipelines                    |
| Container     | Docker                             |
| Security      | OWASP Dependency-Check, PMD, SpotBugs |
| Monitoring    | Spring Boot Actuator + `/health` endpoint |
| Database      | H2 (in-memory, for demo)           |

---

## 📦 Features

- ✅ REST API: `/api/collateral/deposit`, `/withdraw`, `/webhook`
- ✅ CI: Linting, SAST, unit tests, and Docker image build
- ✅ CD: Docker push to ACR → auto-deploy to AKS
- ✅ Health check endpoint: `/actuator/health`
- ✅ Exposed via LoadBalancer w/ public IP

---

## 🚀 CI/CD Flow (Azure Pipelines)

```plaintext
git push → build.gradle → Dockerfile → ACR (image) → AKS Deployment
```

| Step             | Tool / Feature       |
|------------------|----------------------|
| Build + Test     | Gradle + JUnit       |
| Lint + Scan      | Checkstyle, PMD, SpotBugs |
| SAST / CVE Check | OWASP Dependency-Check |
| Test Coverage    | JaCoCo               |
| Container Build  | Docker               |
| Registry Push    | Azure Container Registry (ACR) |
| Deployment       | Azure Kubernetes Service (AKS) |

---

## ✅ Live Environment

| Description         | Endpoint                                  |
|---------------------|-------------------------------------------|
| Healthcheck         | `http://<external-ip>/actuator/health`    |
| Deposit API         | `POST /api/collateral/deposit`            |
| Example curl:       | `curl -X POST http://<ip>/api/collateral/deposit -d "clientId=abc123&amount=100"` |

_Replace `<external-ip>` with your assigned LoadBalancer IP._

---

## 🔎 Code Quality Artifacts

| Tool           | Path                                |
|----------------|-------------------------------------|
| **JUnit**      | `build/test-results/test`           |
| **Checkstyle** | `build/reports/checkstyle`          |
| **PMD**        | `build/reports/pmd`                 |
| **SpotBugs**   | `build/reports/spotbugs`            |
| **JaCoCo**     | `build/reports/jacoco/test/html`    |
| **OWASP DC**   | `dependency-check-report.html`      |

---

## 📌 CVE Sample Findings (OWASP)

- `tomcat-embed-core` — ⚠️ (Critical)
- `logback-core, spring-webmvc, bouncycastle` — ⚠️ High
- `commons-compress, h2, velocity-engine-core` — ⚠️ Medium

---

## 🔮 Future Enhancements
- [ ] PostgreSQL + Persistent Volume
- [ ] SonarQube for centralized code metrics
- [ ] Ingress controller / API Gateway
- [ ] SBOM generation (Syft/CycloneDX)
- [ ] Trivy container scanning (last gate before deployment)
- [ ] Multi-service Helm charts (Collateral Service + Wallet Service + others)

---

## 🧪 Local Dev Instructions

```bash
# Build and run
./gradlew clean build
./gradlew bootRun

# Open coverage report
open build/reports/jacoco/test/html/index.html
```

---

## 🔗 Credits

Designed for institutional crypto custody research.  
Syafiq Rokman.
