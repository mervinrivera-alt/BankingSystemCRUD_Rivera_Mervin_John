# BankingSystemCRUD_Rivera_Mervin_John
# Bank Management System (v1)

A Java Swing-based desktop application designed to handle standard core banking operations securely and efficiently. The system features a responsive, dynamic UI with smooth sliding animations for navigation, modular database operations, and real-time transaction processing.

---

## 🛠️ System Description

The **Bank Management System** is built using a clean, layered architectural pattern dividing user interface logic, database handlers, and transactional processing rules.

### Core Modules & Architecture
* **`Bank_Systems_v1.java`**: The main execution entry point ensuring thread-safe UI rendering.
* **`Frame.java`**: The presentation layer. Manages views using a `CardLayout` architecture to swap between dashboards, registration forms, and transaction screens seamlessly. Features a custom animated sidebar navigation pane.
* **`DBConnection.java`**: The persistence manager handling raw JDBC connectivity and connection pooling.
* **`CRUD_OP.java`**: The Data Access Object (DAO) layer. Encapsulates all Create, Read, Update, and Delete operations. 
* **`transaction.java`**: The financial engine processing deposits, withdrawals, and balance checks.
* **`INFO.java`**: Data transfer objects (DTO) holding transient runtime states and operational metadata.

---

## 📊 Entity-Relationship Diagram (ERD) Explanation

The application operates on a relational database model consisting of core entities working in harmony to guarantee ledger consistency.

### 1. `Accounts` / `Users` Entity
Stores specific client demographics and current financial standing.
* **Primary Key**: `account_number` (Unique identifier for routing funds)
* **Attributes**: `username`, `password_hash`, `email`, `phone`, `current_balance`, `account_type`

### 2. `Transactions` Entity
Maintains immutable ledgers of all moving capital inside the bank ecosystem.
* **Primary Key**: `transaction_id`
* **Foreign Key**: `account_number` (References the `Accounts` entity)
* **Attributes**: `transaction_type`, `amount`, `destination_account`, `timestamp`

### Logical Relationships
* **One-to-Many:** An **Account** can execute **Many Transactions**. 
* **Self-Referencing:** For fund transfers, the `Transactions` entity links an originating `account_number` to a secondary `destination_account`.

---

## 🚀 How to Run the Program (Technical Setup)

### Prerequisites
* **Java Development Kit (JDK)**: Version 11 or higher.
* **Database Engine**: MySQL Server.
* **JDBC Dependency**: MySQL Connector/J driver `.jar` file.

### Installation
1. **Initialize Database:** Run the provided SQL scripts to create `bank_db` and the required `users` and `transactions` tables.
2. **Configure Credentials:** Open `DBConnection.java` and update the `URL`, `USER`, and `PASS` variables to match your local MySQL server.
3. **Compile and Run:** ```bash
   javac -d bin src/bank_systems_v1/*.java
   java -cp bin;path/to/mysql-connector-j.jar bank_systems_v1.Bank_Systems_v1

### 💻 How to Use the Banking System
Once the application is running, you can perform standard banking operations through the graphical user interface.

1. Navigating the Menus
Click the Menu Icon (top left) to trigger the animated sidebar.

Use the sidebar buttons to seamlessly switch between the Dashboard, Account Registration, and Transactions screens.

2. Registering a New Client
Open the Account Management screen.

Fill out the client's personal details: First Name, Last Name, Email, and Phone Number.

Select the Account Type (e.g., Savings, Checking) and input the initial Balance.

Click Save to generate the new account and store it securely in the database.

3. Processing Transactions
Navigate to the Transactions window.

Select an existing account from the dropdown menu.

Choose a Transaction Type:

Deposit: Enter the amount. The system will add it to the account and log the transaction.

Withdrawal: Enter the amount. The system checks if the current balance is sufficient. If funds are insufficient, the transaction will automatically be blocked.

Click Submit to finalize the movement of funds.

4. Viewing System Logs
Access the main data tables on the dashboard or transaction screens to view real-time updates.

Every successful deposit and withdrawal instantly reflects in the Transaction History, showing the account ID, transaction type, amount, and timestamp.
