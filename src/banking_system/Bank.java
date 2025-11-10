package banking_system;

import java.sql.*;
import java.util.Scanner;

public class Bank {
    private Scanner sc = new Scanner(System.in);

    public void createAccount() {
    	final String state="Create";
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Full Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Address: ");
            String address = sc.nextLine();

            System.out.print("Enter Contact Number: ");
            String contact = sc.nextLine();

            while (contact.length() != 10 || !contact.matches("\\d{10}")) {
                System.out.println(" Invalid Contact! Must be 10 digits.");
                System.out.print("Set 10-digit Number: ");
                sc.nextLine();
                break;
            	 
            }
            
            
            System.out.print("Set 4-digit PIN: ");
            String pin = sc.nextLine();
      	
            while (pin.length() != 4 || !pin.matches("\\d{4}")) {
                System.out.println(" Invalid PIN! Must be 4 digits.");
                System.out.print("Set 4-digit PIN: ");
                sc.nextLine();
                break;
            	 
            }

            Account acc = new Account(name, address, contact, pin);

            String sql = "INSERT INTO users (account_number, full_name, address, contact, pin, balance,Last_Tran_Status) VALUES (?, ?, ?, ?, ?, ?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, acc.getAccountNumber());
            ps.setString(2, acc.getFullName());
            ps.setString(3, acc.getAddress());
            ps.setString(4, acc.getContact());
            ps.setString(5, acc.getPin());
            ps.setDouble(6, acc.getBalance());
            ps.setString(7, state);
            ps.executeUpdate();
            System.out.println(" Account created successfully!");
            System.out.println("Your Account Number: " + acc.getAccountNumber());

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(" Account already exists!");
        } catch (SQLException e) {
            System.out.println(" Database error: " + e.getMessage());
        }
    }

    private boolean verifyPin(Connection conn, String accNo, String pin) throws SQLException {
        String query = "SELECT * FROM users WHERE account_number = ? AND pin = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, accNo);
        ps.setString(2, pin);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public void deposit() {
        try (Connection conn = DBConnection.getConnection()) {
        	String state="Deposite";
            System.out.print("Enter Account Number: ");
            String accNo = sc.nextLine();

            System.out.print("Enter PIN: ");
            String pin = sc.nextLine();

            if (!verifyPin(conn, accNo, pin)) {
                System.out.println(" Invalid Account Number or PIN!");
                return;
            }

            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            if (amount <= 0) {
                System.out.println(" Invalid amount!");
                return;
            }

            String sql = "UPDATE users SET  balance = balance + ? , Last_Tran_Status = ? WHERE account_number = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, state);
            ps.setString(3, accNo);
            
            ps.executeUpdate();

            System.out.println(" Deposit successful!");

        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    public void withdraw() {
    	final String state="Withdraw";
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Account Number: ");
            String accNo = sc.nextLine();

            System.out.print("Enter PIN: ");
            String pin = sc.nextLine();

            if (!verifyPin(conn, accNo, pin)) {
                System.out.println(" Invalid Account Number or PIN!");
                return;
            }

            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            if (amount <= 0) {
                System.out.println(" Invalid amount!");
                return;
            }

            // Check balance
            String query = "SELECT balance FROM users WHERE account_number = ?";
            PreparedStatement check = conn.prepareStatement(query);
            check.setString(1, accNo);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance < amount) {
                    System.out.println(" Insufficient balance!");
                    return;
                }
            }

            String sql = "UPDATE users SET  balance = balance - ? , Last_Tran_Status = ? WHERE account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setString(2, state);
            ps.setString(3, accNo);
            ps.executeUpdate();

            System.out.println(" Withdrawal successful!");

        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    public void checkBalance() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Account Number: ");
            String accNo = sc.nextLine();

            System.out.print("Enter PIN: ");
            String pin = sc.nextLine();

            if (!verifyPin(conn, accNo, pin)) {
                System.out.println(" Invalid Account Number or PIN!");
                return;
            }

            String sql = "SELECT balance FROM users WHERE account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(" Current Balance: " + rs.getDouble("balance"));
            }

        } catch (SQLException e) {
            System.out.println(" Database error: " + e.getMessage());
        }
    }
}
