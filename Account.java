import java.util.*;                // imports java.util package

public class Account {                    // creates "Account" class
    
    Account(String type){        // constructor with parameter
        this.type = type;
        this.balance = 0;
        this.accountNumber = nextNumber++;    
    }
    
    private static Account[] accounts = new Account[10];                // creates array of accounts
    private final int accountNumber;                    // creates "accountNumber" int
    private static int nextNumber = 10000;
    private String type;                                // creates "type" String
    private int balance;                                // creates "balance" int
    
    static Scanner scan = new Scanner(System.in);
    
    static void increaseAccountArray(){                                    // method to create larger arrays to accommodate more accounts
        Account[] incAccounts = new Account[accounts.length + 10];        // creates new array 10 larger than accounts
        for(int i = 0; i < accounts.length; i++) {                        // copies all accounts to new array
            incAccounts[i] = accounts[i];
                                                
        }
        accounts = incAccounts;    // sets old array equal to new array
    }
    
    public String getType() {                        // getter for type
        return this.type;
    }

    public int getAccNum() {                        // getter for account number
        return this.accountNumber;
    }
    public int getBal() {                            // getter for balance
        return balance;
    }
    public void setBal(int newBal) {
        this.balance = newBal;
    }
    public static Account[] getAccounts() {                // getter for accounts
        return accounts;        
    }

    public static Account createAccount() {                                            // creates new account 
        
        System.out.println("What type of account would you like to create? (Savings, Checking, Loan)");
        String type = scan.next();                                                    // gets new account type
    
        Account newAccount = new Account(type);                                        // creates new account
        
        for(int i = 0; i < accounts.length; i++) {                            
            if(i == accounts.length-1) {                                            // checks if size of accounts array should be increased,
                increaseAccountArray();                                                // invokes increaseAccountArray method if so
            }
            if(accounts[i] == null) {
                accounts[i] =  newAccount;                                            // finds the first open element in accounts array and assigns newAccount to it
                break;
            }
        }
        return newAccount;
    }    
    

    public static int searchAccounts(int accountNumber) {                    // searches accounts array and returns index of specified account
        
        int index = 0;
        for(int i = 0; i < accounts.length; i++) {
            if(accounts[i] != null) {
                if (accounts[i].getAccNum() == accountNumber) {
                    index = i;
                }
            }
        }
        return index;
    }
    
}
