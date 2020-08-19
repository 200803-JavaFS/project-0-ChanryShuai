package com.revature.utils;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.UserDAO;
import com.revature.dao.AccountDAO;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.service.EmployeeService;
import com.revature.service.UserTransaction;

public class ConsoleUtil {

	private static final Scanner sc = new Scanner(System.in);
	private static final Logger consoleLog = LogManager.getLogger(ConsoleUtil.class);
	private UserDAO userDAO = new UserDAO();
	private AccountDAO accountDAO = new AccountDAO();

	private EmployeeService es = new EmployeeService();
	private UserTransaction ut = new UserTransaction();

	public void beginApp() {
		consoleLog.info("Bank Application Started");
		System.out.println("        **** Welcome to Krusty Krabs Bank **** \n"
				+ "+++ We make good patties & We take care of your money +++ \n"
				+"\n"
				+ "How may I assist you today?"
				+"\n");
		System.out.println("[1] log in as an existing user");
		System.out.println("[2] register as a new client");
		System.out.println("[0] exit");
		System.out.println("Enter option: ");

		String welcome = sc.nextLine();

		switch (welcome) {
		case "1":
			logIn();
			break;
		case "2":
			createCustomer();
			break;
		case "0":
			System.out.println("Exiting... Thank you for visting!");
			break;
		default:
			System.out.println("Invalid input. Please enter a number from the menu...");
			beginApp();
			break;
		}

	}

	private void logIn() {

		consoleLog.info("Logging in...");

		System.out.println("How would you like to log in?");
		System.out.println("[1] User Name");
		System.out.println("[2] User ID");
		System.out.println("[0] exit");
		System.out.println("Enter option: ");

		String clogIn = sc.nextLine();

		switch (clogIn) {
		case "1":
			userNameLog();
			break;
		case "2":
			userIDLog();
			break;
		case "0":
			System.out.println("Exiting... Thank you for visting!");
			break;
		default:
			System.out.println("Invalid input. Please enter a number from the menu...");
			beginApp();
			break;
		}
	}

	private void userIDLog() {
		boolean authenticated = false;

		int IDInput;
		String pInput;


		System.out.println("Please input your userID: ");
		IDInput = sc.nextInt();
		User u = userDAO.getUserByID(IDInput);
		sc. nextLine();
		
		if (u != null) {
			System.out.println("Please input your password: ");
			pInput = sc.nextLine();
			// if (u.getPassword() != null && u.getUsername() != null) {

			if (pInput.equals(u.getPassword())){
				consoleLog.info("Welcome " + u.getFirstname()+u.getLastname());
				authenticated = true;
				loggedIn(u);
			} else {
				System.out.println("Incorrect password or username. Try Again");
				System.out.println();
				userIDLog();
			}
		} else {
			System.out.println("Must enter a valid userID.");
			beginApp();
		}
	}

	private void userNameLog() {
		boolean authenticated = false;

		String uInput;
		String pInput; 
		
		System.out.println("Please input your username: ");
		uInput = sc.nextLine();
		User u = userDAO.getUserByName(uInput);

		if (u != null) {
			System.out.println("Please input your password: ");
			pInput = sc.nextLine();

			if (uInput.equals(u.getUsername()) & pInput.equals(u.getPassword())) {
				consoleLog.info("Welcome " + u.getUsername());
				authenticated = true;
				loggedIn(u);
			} else {
				System.out.println("Incorrect password or username.");
				userNameLog();
			}
		} else {
			System.out.println("Username not registered. Please try again.");
			beginApp();
		}
	}

	private void loggedIn(User u) {

		System.out.println("How may I assist you today?");

		if (u.getType().equals("employee")) {

			String emp = sc.nextLine();
			consoleLog.info("Logged in as an employee -- ");
			System.out.println("[1] See all accounts");
			System.out.println("[2] See one account");
			System.out.println("[3] See all users");
			System.out.println("[4] See one user");
			System.out.println("[0] exit");

			switch (emp) {
			case "0":
				System.out.println(u.getUsername() + " logging out...");
				u = null;
				beginApp();break;
			case "1":
				getAllAccs(u);
				break;
			case "2":
				getOneAcc(u);
				break;
			case "3":
				getAllUsers(u);
				break;
			case "4":
				getOneUser(u);
				break;
			default:
				System.out.println("Invalid input. Please try again.");
			}
			loggedIn(u);
		} 
		if (u.getType().equals("administrator")) {
			
			consoleLog.info("Logged in as an admin -- ");
			System.out.println("[1] See all accounts");
			System.out.println("[2] See one account");
			System.out.println("[3] See all users");
			System.out.println("[4] See one user");
			System.out.println("[5] Perform Transactions");
			System.out.println("[6] Cancel account");
			System.out.println("[0] exit");
			
			String ad = sc.nextLine();
			switch (ad) {
			case "0":
				System.out.println(u.getUsername() + " logging out...");
				u = null;
				beginApp();break;
			case "1":
				getAllAccs(u);
				break;
			case "2":
				getOneAcc(u);
				break;
			case "3":
				getAllUsers(u);
				break;
			case "4":
				getOneUser(u);
				break;
			case "5":
				transaction(u);
				break;
			case "6":
				deleteAcc(u);
				break;
			default:
				System.out.println("Invalid input. Please try again.");
				loggedIn(u);
			}
		}
		if (u.getType().equals("client")) {
			consoleLog.info("Logged in as a client -- ");
			System.out.println("[1]. See my account");
			System.out.println("[2]. Perform transactions");
			System.out.println("[3]. Open a new account");
			System.out.println("[0]. log out");
			System.out.println("Enter option: ");

			int cl = sc.nextInt();
			sc.nextLine();
			switch (cl) {
			case 0:
				System.out.println("Thank you for banking with us. Logging out...");
				u = null;
				beginApp();break;
			case 1:
				getOneUser(u);
				break;
			case 2:
				transaction(u);
				break;
			case 3:
				createAcc(u);
				break;
			default:
				System.out.println("Invalid input. Please try again.");
				loggedIn(u);break;
			}
		}
	}
 
	private void createAcc(User u) {
		
		consoleLog.info("Openning a new account..."); 
		
		String userFK = u.getUsername();
		Account a = new Account();
		
		System.out.println("Please choose an account type: [c]checking / [s]saving");
		String choice = sc.nextLine().toLowerCase();
		switch(choice) {
		case"c":
			a.setAccType("checking"); break;
		case "s": 
			a.setAccType("saving"); break;
		default: System.out.println("Invalid input. Please re-enter your choice.");
			createAcc(u);break;
		}
		
		System.out.println("Please deposit a positive value to activate the account:");
			float in = sc.nextFloat();
			if (in >0) {
				a.setBalance(in); 
				a.setApproved(true);
				a.setUserFK(userFK);
				
			AccountDAO ud = new AccountDAO(); 
			ud.createAcc(a);
			
			consoleLog.info("New Account opened");
			System.out.println("User: " + u.getFirstname() + u.getLastname());
			System.out.println("Username: " + u.getUsername());
			System.out.println("Updated Accounts: ");
			for(Account acc: ud.getAccsByUser(u.getUsername())){
						System.out.println(acc);
					};
			loggedIn(u);
			}else 
			{System.out.println("Error. Please try again."); createAcc(u);}
		
	}		
	

	private void deleteAcc(User u) {
		System.out.println("Enter the accID you would like to cancel: ");
		int i = sc.nextInt();
		sc.nextLine();
		Account a = ut.getAccByID(i);
		ut.deleteAcc(a);
		consoleLog.info("Account canceled...");
		loggedIn(u);
	}

	private void transaction(User u) {

		System.out.println("Select a transaction:");
		System.out.println("[1]. deposit");
		System.out.println("[2]. withdraw");
		System.out.println("[3]. transfer");
		System.out.println("[0]. log out");
		System.out.println("Enter option: ");

		String option = sc.nextLine();
		sc.nextLine();

		switch (option) {
		case "0":
			System.out.println("Thank you. Logging out...");
			u = null;
			beginApp();
		case "1":
			deposit(u);
			break;
		case "2":
			withdraw(u);
			break;
		case "3":
			transfer(u);
			break;
		default:
			System.out.println("Invalid input. Please try again.");
			transaction(u);
		}
	}

	private void transfer(User u) {
		System.out.println("Please enter the accID you are transferring from: ");
		int i = sc.nextInt();
		sc.nextLine();
		Account from = ut.getAccByID(i);
		if(from.isApproved() == false) {
			System.out.println("Sorry, account is closed.");
			loggedIn(u);
		}
		
		System.out.println(from);
		System.out.println("Please enter the accID you are transferring to: ");
		int j = sc.nextInt();
		sc.nextLine();
		Account to = ut.getAccByID(j);
		if(to.isApproved() == false) {
			System.out.println("Sorry, account is closed.");
			loggedIn(u);
		}
		
		System.out.println(to);
		float fbalance = from.getBalance();
		float tbalance = to.getBalance();

		System.out.println("Enter the amount you would like to transfer: ");
		float amount = sc.nextFloat();
		if (fbalance >= amount & amount > 0) {
			fbalance -= amount;
			tbalance += amount;
			
			from.setBalance(fbalance);
			to.setBalance(tbalance);
			System.out.println(
					
					"You have successfully transferred " + amount + " from account " + from.getAccID() + " to account " + to.getAccID());
		} 
		if (ut.updateAcc(from) || ut.updateAcc(to)) {
			System.out.println("Your new accounts' info: ");
			System.out.println(from);
			System.out.println(to);
			transaction(u);
		} else {
			System.out.println("Something went wrong please try again");
			transfer(u);
		}
	}

	private void withdraw(User u) {
		System.out.println("Please enter the accID you are withdrawing from: ");
		int i = sc.nextInt();
		sc.nextLine();
		Account acc = ut.getAccByID(i);
		if(acc.isApproved() == false) {
			System.out.println("Sorry, account is closed.");
			loggedIn(u);
		}
		
		float balance = acc.getBalance();

		System.out.println("Please enter amount to withdraw: ");
		float amount = sc.nextFloat();
		if (amount > 0) {
			if (balance >= amount) {
				balance -= amount;
				acc.setBalance(balance);
				System.out.println("You have withdrawn " + amount + ". " + "Your balance is now: " + balance);
			} else {
				System.out.println("Sorry, withdrawl can't be processed due to insufficient funds.");
			}

		} else {
			System.out.println("Please enter a positive amount to withdrawl.");
		}
		if (ut.updateAcc(acc)) {
			System.out.println("Your new account info: ");
			System.out.println(acc);
		} else {
			System.out.println("Something went wrong please try again");
			withdraw(u);
		}

	}

	public void deposit(User u) {
		System.out.println("Please enter the accID you are depositting into: ");
		int i = sc.nextInt();
		sc.nextLine();
		Account acc = ut.getAccByID(i);
		if(acc.isApproved() == false) {
			System.out.println("Sorry, account is closed.");
			loggedIn(u);
		}
		
		float balance = acc.getBalance();

		System.out.println("Please enter an amount to deposit: ");
		float amount = sc.nextFloat();
		if (amount > 0) {
			balance += amount;
			acc.setBalance(balance);
			System.out.println("You have successfully deposited " + amount + ". " + "Your balance is now: " + balance);
		
		if (ut.updateAcc(acc)) {
			System.out.println("Your new account info: ");
			System.out.println(acc);
			transaction(u);
		} else { 
			System.out.println("Something went wrong please try again");
			deposit(u);
		}} else {
			System.out.println("Please deposit a positive amount.");
			deposit(u);
		}
	}

	private void getOneUser(User u) {
		System.out.println("Please enter the userID of the user you are trying to look at: \n ");
		int i = sc.nextInt();
		sc.nextLine();
		
		User a = es.getUserByID(i);
		AccountDAO ud = new AccountDAO(); 
		
		System.out.println("User info: " + a);
		System.out.println("Accounts: "); 
		for(Account acc: ud.getAccsByUser(a.getUsername())){
			System.out.println(acc);
		}
		loggedIn(u);
	}

	private void getOneAcc(User u) {

		System.out.println("Please enter the accID of the account you are trying to look at: ");
		
		int i = sc.nextInt();
		sc.nextLine();
		Account a = ut.getAccByID(i);
		
		
		if (u.getType().equals("administrator")| u.getType().equals("employee")) {
			System.out.println("Account info: " + a);
			System.out.println("Change account status? [Y]/[N]");
			String s = sc.nextLine().toLowerCase();
			if (s.equals("y")) {
				updateStatus(u, a);
			} else {
				loggedIn(u); 
			}
		} else if (a.getUserFK().equals(u.getUsername())){
			System.out.println(a);
			loggedIn(u);
		}else {System.out.println("Account does not exist for user: "+u.getFirstname());
			getOneAcc(u);
		}
		
	}

	private void updateStatus(User u, Account a) {
		consoleLog.info("Changing account status...");
		a.setApproved(!a.isApproved());
		System.out.println(a);
		if (ut.updateAcc(a)) {
			System.out.println("Account status changed: ");
			System.out.println(a);
			loggedIn(u);
		} else {
			System.out.println("Something went wrong please try again");
			updateStatus(u, a);
		}
	}

	private void getAllAccs(User u) {
		
		System.out.println("Here are all the accounts: ");
			List <Account> list = ut.getAllAccs(); 
		for (Account a : list) {
			System.out.println(a);
		}	
			
		System.out.println("[1] View one account \n" + "[0] return");

		String answer = sc.nextLine();
		switch (answer) {
		case "1":
			getOneAcc(u);
			break;
		case "0":
			System.out.println("Ok. Returning to main menu...");
			loggedIn(u);
			break;
		default:
			System.out.println("Invalid input. Please try again.");
			getAllAccs(u);
		}
	}

	private void getAllUsers(User u) {
		
		System.out.println("Here are all the users: ");
		if(u.getType().equals("administrator")) {
			List <User> list = es.getAllUsers(); 
		for (User a : list) {
			System.out.println(a);
		}
		}
		if(u.getType().equals("employee")) {
			List<User> clist = es.getAllClients();
			for (User a : clist) {
				System.out.println(a);
			}
		}
		System.out.println("Are you looking for a specific user? [Y]/[N]");
		String answer = sc.nextLine().toLowerCase();
		switch (answer) {
		case "y":
			getOneUser(u);
			break;
		case "n":
			System.out.println("Ok. Returning to main menu...");
			loggedIn(u);
		default:
			System.out.println("Invalid input. Please enter [Y] or [N]");
			getAllUsers(u);
		}
	}

	private void createCustomer() {

		consoleLog.info("New Client Registration");
		String type = "client";
		
		System.out.println("Please enter your first name: ");
		String firstname = sc.nextLine();

		System.out.println("Please enter your last name:");
		String lastname = sc.nextLine();

		System.out.println("Please enter your email address: ");
		String email = sc.nextLine();

		UserDAO un = new UserDAO();
		if (un.getUserByName(email) != null) {
			System.out.print("Email address already registered. Please log in.");
			beginApp();
		} else {
			String username = email;
			consoleLog.info("New Username Created: " + username);
			System.out.println("Please enter a password: ");
			String password = sc.nextLine();

			User u = new User(username, password, firstname, lastname, type);
			UserDAO ud = new UserDAO();
			ud.createUser(u); 
			
			
			consoleLog.info("New Client Registered");
			consoleLog.info("Username: " + username);
			consoleLog.info("User: "+ u.getFirstname());
			consoleLog.info("UserID: " + ud.getUserByName(username).getUserID());

			loggedIn(u); 
		}
	}

}
