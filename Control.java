/*
 Banking Database
 * This program will create a simple banking database with customers, accounts, and Employees.
 */


import java.util.*;			// imports java.util package

public class Control {

	static Scanner scan = new Scanner(System.in);					// creates scanner

	public static Customer[] customers = new Customer[10];
	

	public static void main(String[] args) {
																													// initializes employee array and creates 3 employees
		Employee[] employees = {new Employee("Chance Cooper", "1280 Iowa Ave, Beaumont, Texas", "Passw0rd"),			// userID == 100				
								new Employee("Tianjian Li", "631 E Virginia Ave, Beaumont, Texas", "TerminatorTJ"),		// userID == 101
								new Employee("Bradley Allen", "212 Georgia Street, Beaumont, Texas", "BAllen21")};		// userID == 102

		
		boolean running = true;
		boolean loggedIn = false;
		
		while(running) {												// initializes primary loop
			
			System.out.println("Enter login type. (Employee or Customer), or enter 'Quit' to quit.");				
				String loginType = scan.next();											// gets user input for login type
			
			if(loginType.equals("Quit")) {
				break;
			}
				
				
			System.out.println("Enter user ID");										
				int userID = scan.nextInt();							// gets user input for user ID
			
			int index = 0;
				
			if(loginType.equals("Customer")){
				index = Customer.searchCustomers(userID);
				Customer currentCustomer = customers[index];
				
				if(currentCustomer == null)
					System.out.println("Invalid user ID");
				
				else {
				loggedIn = true;
				System.out.println("Logged in. Welcome, " + currentCustomer.getName());	
				}
					
				}		// end of if customer is logged in	
					
				
			
			Employee currentEmployee = null;							// creates currentEmployee variable
				
			if(loginType.equals("Employee")) {							// checks to see if employee ID exists
				for(int i = 0; i < employees.length; i++) {
					if(userID == employees[i].getEmployeeID()) {
						index = i;										// sets index equal to Employee's index with matching userID
						currentEmployee = employees[i];					// if input userID is an existing Employees password, sets currentEmployee equal to that Employee object
						break;
						}												// end of second if statement
					else if(i == employees.length-1 & userID !=employees[i].getEmployeeID()) {
							System.out.println("Invalid user ID");
					break;	
					}
				}														// end of for loop
																		
			if(currentEmployee != null) {
			System.out.println("Enter employee password");
				String password = scan.next();											// gets user input for employee password
					if(!password.equals(employees[index].getPassword())) {
						System.out.println("Incorrect password.");
					}
					else {loggedIn = true;
					System.out.println("Logged in. Welcome, " + employees[index].getName() +"!");}
					}		
				
			}	// end of first if statement to check for Employee login
			
			while(loggedIn) {									// initializes secondary loop
					
				int action;
				
				if(loginType.equals("Customer")){
					
					
					
					System.out.println("----------------- \nSelect an Action: \n 1. Change name \n 2. Change address \n 3. View your accounts information \n 4. Logout \n-----------------");
				
					action = scan.nextInt();
						
					switch(action) {	// lets customer change their name
					case 1:{
						System.out.println("Enter new name");
						String newName= scan.next();
						newName += scan.nextLine();
						customers[index].setName(newName);
						System.out.println("Your new name is " + customers[index].getName());
						break;
					}					// end of case 1
					
					case 2:{			// lets customer change their address
						System.out.println("Enter new address");
						String newAddress = scan.next();
						newAddress += scan.nextLine();
						customers[index].setAddress(newAddress);
						System.out.println("Your new address is " + customers[index].getAddress());
						break;
					}					// end of case 2
					
					case 3:{			// lets customer view all account information
						System.out.println("Name: " + customers[index].getName()+ "\nAddress: " + customers[index].getAddress() + "\nAccounts: ");					
						for(int i = 0; i < customers[index].getAccounts().size(); i++) {
							System.out.println("Account number: " + customers[index].getAccounts().get(i).getAccNum() + " (" + customers[index].getAccounts().get(i).getType() + ") " + "Balance: " + customers[index].getAccounts().get(i).getBal());
						}
						break;
						
					}					// end of case 3
					
					case 4:{
						loggedIn = false;
						break;
					}	
					
					}		// end of customer switch case
					
				}		// end of if customer is logged in	

				
				
				if(loginType.equals("Employee")){
					System.out.println("----------------- \nSelect an Action: \n 1. Change name \n 2. Change address \n 3. Add to or subtract from an accounts balance \n 4. Create new account "		// displays all actions employees can perform
				+ "\n 5. Add existing account to existing customer \n 6. Add new customer \n 7. View a customers information \n 8. View an accounts information	\n 9. Logout \n-----------------");
					
					action = scan.nextInt();
					
					switch(action) {
					case 1: {
						System.out.println("Enter new name");
						String newName = scan.next();
						newName += scan.nextLine();
						employees[index].setName(newName);
						System.out.println("Your new name is " + employees[index].getName());
						break;
					}
				
					case 2:{
						System.out.println("Enter new address");
						String newAddress = scan.next();
						newAddress += scan.nextLine();
						employees[index].setAddress(newAddress);
						System.out.println("Your new address is " + employees[index].getAddress());
						break;
					}
					
					case 3:{																		// edit account balance case
						System.out.println("Enter account number");			
						int accNum = scan.nextInt();
						
						int accountIndex = Account.searchAccounts(accNum);
							
						Account currentAccount = Account.getAccounts()[accountIndex];
						System.out.println("This account has a balance of " + currentAccount.getBal());
						
						System.out.println("Input blance change.");
						int balanceChange = scan.nextInt();														
						currentAccount.setBal(currentAccount.getBal() + balanceChange);
							
						System.out.println("New account balance is "+ currentAccount.getBal());
						
						break;
					}
				
	
					case 4:{
					
						System.out.println("Enter customer ID you wish to add account to");					// asks for user input
						int custID = scan.nextInt();	
						// sets custID = user input					
						Account newAccount = Account.createAccount();										// creates new account
						System.out.println("New account number is " + newAccount.getAccNum());			
						
						int accountNumber = newAccount.getAccNum();											// sets accountNumber = new account number
						
						Customer.addAccToCust(custID, accountNumber);										// adds newly created account to an existing customer					
						break;
					}	
				
					case 5:{																				// adds existing account to existing customer
					
						System.out.println("Enter customer ID you wish to add account to");					// asks for user input
						int custID = scan.nextInt();														// sets custID = user input					
						System.out.println("Enter account number that will be added.");						// asks for user input
						int accountNumber = scan.nextInt();													// sets accountNumber = user input
					
						Customer.addAccToCust(custID, accountNumber);										// invokes addAccToCust method to add input account to input customer
						break;
					}
						
					case 6:{
			
						Customer newCustomer = Customer.createCustomer();									// creates new customer
						int custID = newCustomer.getCustID();												// gets ID of new customer
						
						Account newAccount = Account.createAccount();										// creates new account
						int accountNumber = newAccount.getAccNum();											// gets account number of new account
						
						Customer.addAccToCust(custID, accountNumber);										// invokes method to add new account to new customer
						System.out.println("New account with account number " + newAccount.getAccNum() + " has been added to new customer with customer ID " + newCustomer.getCustID());
					
						break;	
					}
					
				
					case 7:{
						
						System.out.println("Enter customer ID");											
						int custID = scan.nextInt();														// gets customer ID
						int customerIndex = Customer.searchCustomers(custID);								// searches customers for a Customer with matching ID, returns its index
						Customer currentCust = customers[customerIndex];									
																											// prints all information of customer
						System.out.println("Name: " + currentCust.getName() + "\nAddress: " + currentCust.getAddress() + "\nAccounts: " );
						for(int i = 0; i < currentCust.getAccounts().size(); i++) {
							System.out.println("Account number: " + currentCust.getAccounts().get(i).getAccNum() + " (" + currentCust.getAccounts().get(i).getType() + ") " + "Balance: " + currentCust.getAccounts().get(i).getBal());
						}
						break;
					}
					
					case 8:{
						
						System.out.println("Enter account number");									
						int accNum = scan.nextInt();														// gets account number
						int accountIndex = Account.searchAccounts(accNum);									// searches accounts for Account with matching number, returns its index
						Account currentAccount = Account.getAccounts()[accountIndex];
																											//prints all information of account
						System.out.println("Account Number:" + currentAccount.getAccNum() + "\nAccount type: " + currentAccount.getType() + "\nAccount balance: " + currentAccount.getBal());
					break;
					}
					

					case 9:{
						loggedIn = false;																	// logs out
						break;
					}	
					
					}	// end of switch case				
				
				}		// end of if employee is logged in
				
			}		// end of secondary loop
				
		}		//end of primary loop
			
	}		// end of main method
	
}		// end of control class
