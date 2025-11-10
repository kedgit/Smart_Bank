package banking_system;

import java.util.Random;

public class Account {
    private String accountNumber;
    private String fullName;
    private String address;
    private String contact;
    private String pin;
    private double balance;

    public Account(String fullName, String address, String contact, String pin) {
        this.accountNumber = generateAccountNumber();
        this.fullName = fullName;
        this.address = address;
        this.contact = contact;
        this.pin = pin;
        this.balance = 0.0;
    }

    private String generateAccountNumber() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public String getAccountNumber() { 
    	return accountNumber; 
    }
    public String getFullName() { 
    	return fullName; 
    }
    public String getAddress() { 
    	return address; 
    }
    public String getContact() { 
    	return contact;
    }
    public String getPin() { 
    	return pin; 
    }
    public double getBalance() { 
    	return balance; 
    }
}
