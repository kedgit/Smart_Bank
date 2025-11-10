🏦 SmartBank — Java Console Banking Management System
A simple yet functional Bank Management System built in Java with Postgresql database integration. This project simulates common banking operations such as account creation, deposits, withdrawals, balance checking etc.

🚀 Features

👤 User Features
Create Account — Register a new customer with personal details.
Deposit Money — Add funds to an existing account.
Withdraw Money — Withdraw funds (with balance validation).
Check Balance — View current balance.
Exit          - Exit Application

🧰 Account Access Valid
  take account number
  4 digit pin set at account creation time
  
🏗️ Project Structure
Bank_Management_System/
├── bin/
│   └── banking_system/    
│       ├── Main.class
│       ├── Account.class 
│       ├── DBConnection.class
│       ├── Bank.class 
├── src/
│   └─── banking_system/
│       ├── Main.java              # Entry point (menu system)
│       ├── Bank.java              # User account operations
│       ├── Account.java           # Account model 
│       ├── DBConnection.java      # JDBC connection logic
│       
├── Referenced Libraries/
│   └── postgresql-connecter-42.7.8.jar
└── README.md                      # Documentation 

⚙️ Technologies Used
Category	      Technology
Language	    Java (JDK 17+)
Database	    PostgreSql
Connection	  JDBC (PostgreSql Connector)
UI	          Console-based text menus
Tool	        Eclipse

🗃️ Database Summary

1️⃣ Create the Database
CREATE DATABASE bank;

2️⃣ Connect To Database
\c bank 

3️⃣ Create users Table
CREATE TABLE users (
    id serial PRIMARY KEY,
    account_number varchar(15),
    full_name varchar(100),
    address varchar(255),
    contact varchar(20),
    pin varchar(10),
    balance double default 0,
    account_create_date timestamp default now(),
    last_tran_status varchar(20);
);

🔌 Configure Database Connection

Edit DBConnection.java:

private static final String URL = "jdbc:postgresql://localhost:5432/bank";
private static final String USER = "postgre";              // your postgresql username
private static final String PASSWORD = "your_password"; // your postgresql password

▶️ Running the Project

✅ Option 1: Using an IDE (Recommended)
Open project in IntelliJ / Eclipse / VS Code.
Make sure postgresql is running.
Update credentials in DBConnection.java.
Run Main.java.

💻 Option 2: Using Command Line
dir lib
javac -encoding UTF-8 -cp "Referenced Libraries/postgresql-connector-j-42.7.8.jar" -d bin src/main/*.java
java -cp "bin;Referenced Libraries/postgresql-connector-j-42.7.8.jar" bank_system.Main

🧠 Program Flow
Start → Main Menu → Choose Operation

🧰 Validation Rules
Field	Rule
Name	Alphabets only, min 3 characters
Contact	Exactly 10 digits
Contact	Exactly 4 digits
Amount	Must be positive

👨‍💻 Author
Kedar Mhetre Java Developer GitHub: https://github.com/kedgit/Smart_Bank
