# Bank Account Kata

## 📌 Project Overview
This project implements a **Bank Account Kata** following **Hexagonal Architecture**, **TDD (Test-Driven Development)**, and **BDD (Behavior-Driven Development)** principles. The application provides a REST API to manage a bank account, allowing deposits, withdrawals, and account statements.

---

## 🚀 Features
- **Deposit Money** 💰
- **Withdraw Money** 💸
- **Check Account Balance** 📊
- **Retrieve Account Statement** 📜
- **Prevents Overdrafts (Insufficient Funds Handling)** 🚫

---

## 🛠️ Technologies Used
- **Java 17+**
- **Spring Boot 3.x** (REST API, JPA, MockMvc for testing)
- **Hexagonal Architecture** (Ports & Adapters Pattern)
- **JUnit 5 & Mockito** (TDD Unit Tests)
- **Cucumber (BDD)** (Behavior-Driven Development Tests)
- **Gradle** (Build Tool)
- **H2 Database** (In-memory Database for Testing)
- **PostgreSQL** (For Production Database)
- **Ktlint** (Code Formatting for Kotlin, if used)

---

## 📂 Project Structure
```
src/
├── main/
│   ├── java/com/bank/account/
│   │   ├── application/  # Business Logic (Use Cases)
│   │   ├── domain/       # Domain Model (Entities, Aggregates)
│   │   ├── infrastructure/ # Adapters (Repositories, Controllers, API Interfaces)
│   │   ├── resources/    # Configuration Files
│   │   ├── BankApplication.java  # Main Spring Boot Application
├── test/
│   ├── java/com/bank/account/
│   │   ├── infrastructure/resource/  # Unit Tests (JUnit + Mockito)
│   │   ├── bdd/  # Cucumber Step Definitions
│   │   ├── resources/features/  # BDD Feature Files
```

---

## 📝 API Endpoints
### **1️⃣ Deposit Money**
```http
POST /api/account/deposit?amount=100
```
📌 **Response:**
```json
"Deposit successful"
```

### **2️⃣ Withdraw Money**
```http
POST /api/account/withdraw?amount=50
```
📌 **Response:**
```json
"Withdrawal successful"
```

### **3️⃣ Insufficient Funds**
```http
POST /api/account/withdraw?amount=200
```
📌 **Response:**
```json
"Insufficient funds"
```

### **4️⃣ Check Balance**
```http
GET /api/account/balance
```
📌 **Response:**
```json
150
```

### **5️⃣ Get Account Statement**
```http
GET /api/account/statement
```
📌 **Response:**
```json
[
  {"date":"2025-03-04", "amount":100, "balance":100},
  {"date":"2025-03-05", "amount":-50, "balance":50}
]
```

---

## ✅ Running Tests
### **Run Unit Tests (JUnit & Mockito)**
```sh
./gradlew test
```

### **Run BDD Tests (Cucumber)**
```sh
./gradlew cucumber
```

---

## 🛠️ Setup & Installation
### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/your-repo/bank-account-kata.git
cd bank-account-kata
```

### **2️⃣ Build & Run**
```sh
./gradlew clean build
./gradlew bootRun
```

### **3️⃣ Access API Documentation**
If Swagger is enabled, access:
```sh
http://localhost:8080/swagger-ui/index.html
```

---
