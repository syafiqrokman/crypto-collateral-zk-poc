
# 🛡️ Crypto Collateral zkPoC

A secure, modular proof-of-concept for institutional-grade crypto collateralization.  
Built to demonstrate scalable backend design, decoupled settlement logic, and future-facing zk integration.

---

## 🎯 Project Objective

This was developed as a **post-interview initiative** after speaking with company's technical leadership.

It simulates a **decoupled collateral management system**, designed for **migratability**, **scalability**, and potential integration with **zk-based privacy systems**.

**Goals:**
- ✅ Clean, testable backend design
- ✅ Flexible settlement architecture (Quorum → Ethereum → ZK rollups)
- ✅ ZK circuit wiring capability

---

## 🧠 Architecture Overview

```
Client
  ⇅
API Gateway
  ⇅
Collateral Service (Spring Boot)
   ├── Internal DB (H2/Postgres)
   └── Settlement Adapter
         ├── QuorumAdapter (stub)
         ├── EthereumAdapter (stub)
         └── ZkSettlementAdapter ✅ (demo-ready)
```

---

## ⚙️ Folder Structure

```
.
├── backend/       # Spring Boot service (deposit logic + adapters)
├── zk/            # Circom circuit for ZK deposit proof
├── infra/         # Infra placeholder (AKS, Helm, etc)
├── frontend/      # Optional UI placeholder
├── README.md
```

---

## 🔐 zk Integration (Experimental)

Folder: `zk/`

- `deposit.circom`: Minimal zkSNARK circuit for deposits
- `input.json`: Test input for generating proof
- Expandable to full Circom + Groth16 prover/verifier stack

---

## 📦 What This Demonstrates

- Separation of **business logic** and **settlement logic**
- Ready for swapping Quorum to Ethereum/ZK rollup backends
- Future-facing architecture for privacy-preserving finance
- Shows initiative, scalability, and upgrade readiness

---

## ✅ Status

| Module        | Description                            |
|---------------|----------------------------------------|
| `backend/`    | Spring Boot microservice               |
| `zk/`         | Circom proof-of-concept                |
| `infra/`      | Infra not implemented (coming soon)    |
| `frontend/`   | Placeholder for optional UI            |

---

## 📌 Next Steps

- [ ] Wire Docker + CI/CD
- [ ] Add Helm + AKS support
- [ ] Push unit tests and expand adapter coverage
- [ ] Build UI to visualize zk proof + submit flow

---

## 🗒️ Note

This project was created after interviews with Company's leadership to **deeper alignment** with the company's scaling roadmap, shift-left priorities, and skill in ZK tech.
