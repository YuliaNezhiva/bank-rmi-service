# Distributed Bank RMI Service (Variant 16)

This repository contains a distributed client-server banking application implemented using **Java RMI (Remote Method Invocation)** technology. The system provides remote banking operations with concurrent access support on the server side.

## Project Specification (Variant 16)
The task is to develop a remote banking service where a user can interact with their account from a remote client application. The server is responsible for maintaining all centralized account records and handling concurrent transactions smoothly.

### Features:
* **Open Account:** Create a new unique bank account with an initial cash balance.
* **Deposit Funds:** Remotely add money to a specified account.
* **Withdraw Funds:** Securely withdraw money with automatic server-side balance validation.
* **Get Balance:** Retrieve the live account balance anytime.

---

## Architecture & Technology Stack
* **Language:** Java 17
* **Build System:** Maven
* **Core Technology:** Java RMI (`java.rmi`), Binary Object Serialization (`java.io.Serializable`)
* **Thread Safety:** Handled via thread-safe collections (`ConcurrentHashMap`) and synchronized business logic methods on the server.

---

## Project Structure

```text
bank-rmi-service/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── bank/
                    ├── Account.java        # Serializable model of the bank account
                    ├── BankService.java    # Remote Interface defining banking operations
                    ├── BankServer.java     # Server implementation & RMI Registry launcher
                    └── BankClient.java     # Client application demonstrating remote calls
