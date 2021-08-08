import java.util.*;            // imports java.util package

public class Employee {        // creates Employee class

    static Scanner scan = new Scanner(System.in);
    
    private String name;
    private final int employeeID;
    private static int nextID = 100;
    private String address;
    private String password;    
    
    
                                                                            // default constructor
    Employee(String name, String address, String password){                // constructor with parameters
        this.name = name;
        this.employeeID = Employee.nextID++;
        this.address = address;
        this.password = password;
    }

    public String getName() {                            // getter for name
        return this.name;
    }
    public void setName(String name) {                    // setter for name
        this.name = name;
    }
    public int getEmployeeID() {                        // getter for employeeID
        return employeeID;
    }
    public String getAddress() {                        // getter for address
        return address;
    }
    public void setAddress(String address) {            // setter for address
        this.address = address;
    }
    public String getPassword() {                        // getter for password
        return password;
    }

    public void createAccount() {                // creates new account 
        
        System.out.println("What type of account would you like to create? (Savings, Checking, Loan)");
        String type = scan.next();
        
        Account newAccount = new Account(type);
        
        for(int i = 0; i < Account.getAccounts().length; i++) {
            if(Account.getAccounts()[i] == null) {
                Account.getAccounts()[i] =  newAccount;
                
            }
            
        }
        
    }                
    
    
    public static Customer createCustomer() {                    // creates new customer
        
        System.out.println("Enter new customers name:");
        String name = scan.next();
            name += scan.nextLine();
        System.out.println("Enter new customers address:");
        String address = scan.next();
            address += scan.nextLine();
        
        return new Customer(name, address);        // creates new customer object with parameters
    }
    
    
}
