package Internship;

//public class BankY {
	import java.io.*;
	import java.util.HashMap;
	import java.util.Map;

	public class BankY {
	    private Map<String, Double> accounts;
	    private String dataFile;

	    public BankY(String dataFile) {
	        this.accounts = new HashMap<>();
	        this.dataFile = dataFile;
	        loadData();
	    }

	    private void loadData() {
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
	            accounts = (HashMap<String, Double>) ois.readObject();
	        } catch (FileNotFoundException e) {
	            System.out.println("Data file not found, starting with an empty account list.");
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    private void saveData() {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
	            oos.writeObject(accounts);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void createAccount(String accountId, double initialBalance) {
	        if (accounts.containsKey(accountId)) {
	            throw new IllegalArgumentException("Account already exists.");
	        }
	        accounts.put(accountId, initialBalance);
	        saveData();
	    }

	    public double getBalance(String accountId) {
	        validateAccount(accountId);
	        return accounts.get(accountId);
	    }

	    public void deposit(String accountId, double amount) {
	        validateAccount(accountId);
	        if (amount <= 0) {
	            throw new IllegalArgumentException("Deposit amount must be positive.");
	        }
	        accounts.put(accountId, accounts.get(accountId) + amount);
	        saveData();
	    }

	    public void withdraw(String accountId, double amount) {
	        validateAccount(accountId);
	        if (amount <= 0) {
	            throw new IllegalArgumentException("Withdrawal amount must be positive.");
	        }
	        if (accounts.get(accountId) < amount) {
	            throw new IllegalArgumentException("Insufficient funds.");
	        }
	        accounts.put(accountId, accounts.get(accountId) - amount);
	        saveData();
	    }

	    public void transfer(String fromAccountId, String toAccountId, double amount) {
	        validateAccount(fromAccountId);
	        validateAccount(toAccountId);
	        if (amount <= 0) {
	            throw new IllegalArgumentException("Transfer amount must be positive.");
	        }
	        if (accounts.get(fromAccountId) < amount) {
	            throw new IllegalArgumentException("Insufficient funds.");
	        }
	        accounts.put(fromAccountId, accounts.get(fromAccountId) - amount);
	        accounts.put(toAccountId, accounts.get(toAccountId) + amount);
	        saveData();
	    }

	    private void validateAccount(String accountId) {
	        if (!accounts.containsKey(accountId)) {
	            throw new IllegalArgumentException("Account does not exist.");
	        }
	    }

	    public static void main(String[] args) {
	        BankY bank = new BankY("bank_data.ser");
	        bank.createAccount("123456", 100.0);
	        bank.createAccount("678905", 200.0);
	        bank.deposit("123456", 50.0);
	        bank.withdraw("678905", 70.0);
	        bank.transfer("123456", "678905", 30.0);
	        System.out.println("Balance of 12345: " + bank.getBalance("123456"));
	        System.out.println("Balance of 67890: " + bank.getBalance("678905"));
	    }
	}



