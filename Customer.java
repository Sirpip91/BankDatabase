import java.util.*;				// imports java.util package

public class Customer {
	
	static Scanner scan = new Scanner(System.in);
	
	private ArrayList<Account> customerAccounts = new ArrayList<Account>();
	private String name;
	private final int custID;
	private static int nextID = 1000;
	private String address;
	
															
	Customer(String name,  String address){			// constructor with parameters			// removed parameter Account initialAccount
	this.name = name;
	this.address = address;
	this.custID = Customer.nextID++;
	}	
	
	static void increaseCustomerArray(){											// method to create larger arrays to accommodate more accounts
		Customer[] incCustomers = new Customer[Control.customers.length + 10];		// creates new array 10 larger than accounts
		for(int i = 0; i < Control.customers.length; i++) {										// copies all accounts to new array
			incCustomers[i] = Control.customers[i];										
		}
		Control.customers = incCustomers;	// sets old array equal to new array
	}

	public void editAddress() {									// changes the customers name to user input
		System.out.println("Enter new customer address:");	
		this.address = scan.nextLine();
	}
	public String getName() {									// getter for name
		return name;
	}
	public void setName(String name) {							// setter for name
		this.name = name;
	}
	public int getCustID() {									// getter for customerID
		return custID;
	}
	public String getAddress() {								// getter for address
		return address;
	}
	public void setAddress(String address) {					// setter for address
		this.address = address;
	}
	public ArrayList<Account> getAccounts() {					// getter for customer accounts ArrayList
		return customerAccounts;
	}

	public void showAccounts() {
		System.out.println(customerAccounts);
	}
	
	public void addAccount(Account account) {					//adds account to customers accountList, prevents duplicate accounts for customers
	
		if(customerAccounts.contains(account) == false) {
			this.customerAccounts.add(account);
		}
		else System.out.println("This account already belongs to this customer");

	}
	

	public static int searchCustomers(int custID) {				// searches through all customers for matching customerID
		int index = 0;
		for (int i = 0; i < Control.customers.length; i++) {
			if(Control.customers[i] != null) {
				if(Control.customers[i].getCustID() == custID) {
					index = i;
				}
			}
		}
		return index;
	}
	
	
	public static void addAccToCust(int custID, int accountNumber){										// adds account to customer
	 		
		int customerIndex = Customer.searchCustomers(custID);											// invokes searchCustomers method to search customers[] for object with matching custID and returns its index
		Customer currentCust = Control.customers[customerIndex];										// sets Customer currentCust = customer with matching ID
	 
	 	int accountIndex = Account.searchAccounts(accountNumber);										// invokes searchAccounts method to search accounts[] for object with matching accountNumber and returns its index
		Account currentAcc = Account.getAccounts()[accountIndex];										// sets Account currentAcc = account with matching ID
						
		currentCust.addAccount(currentAcc);																// invokes addAccount method to add currentAccount to currentCusts ArrayList
	 
	 }
	  				
	public static Customer createCustomer() {					// creates new customer
	
		System.out.println("Enter new customers name:");
		String name = scan.next();
			name += scan.nextLine();
		System.out.println("Enter new customers address:");
		String address = scan.next();
			address += scan.nextLine();

		Customer newCustomer = new Customer(name, address);
		
			for(int i = 0; i < Control.customers.length; i++) {
				if(i == Control.customers.length - 1) {				// increases size of customer array if needed
				increaseCustomerArray();	
				}	
				if(Control.customers[i] == null) {
					Control.customers[i] = newCustomer;
					break;
				}
			}
			return newCustomer;	
	} 	
	
}		// end of class
