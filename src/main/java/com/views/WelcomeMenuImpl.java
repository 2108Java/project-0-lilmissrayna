package com.views;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.models.User;
import com.repos.AccountDAO;
import com.repos.AccountDaoImpl;
import com.repos.AccountTypeDAO;
import com.repos.AccountTypeDaoImpl;
import com.repos.TransferDAO;
import com.repos.TransferDaoImpl;
import com.repos.UserDAO;
import com.repos.UserDaoImpl;
import com.repos.UserTypeDAO;
import com.repos.UserTypeDaoImpl;
import com.service.AdminService;
import com.service.AdminServiceImpl;
import com.service.CustomerService;
import com.service.CustomerServiceImpl;
import com.service.EmployeeService;
import com.service.EmployeeServiceImpl;
import com.service.WelcomeService;

public class WelcomeMenuImpl implements WelcomeMenu {
	private static final Logger BankLog = Logger.getLogger(WelcomeMenuImpl.class);
	WelcomeService service;

	public WelcomeMenuImpl(WelcomeService service) {
		this.service = service;
	}

	@Override
	public void display() {
		BankLog.info("Starting welcome menu display.");
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		do {
			options();

			int option = Integer.parseInt(sc.nextLine());
			System.out.println();
			System.out.println();
			switch (option) {
			case 1:
				BankLog.info("Authenticating user.");
				System.out.println("Enter your username:");
				String usernameLogin = sc.nextLine();
				System.out.println("Enter your password:");
				String passwordLogin = sc.nextLine();
				System.out.println();
				User currentUser = service.login(usernameLogin);

				if (currentUser == null) {
					BankLog.warn("User not found.");
					System.out.println("User not found, please make sure you have entered the correct username.");
					break;
				} else if (!currentUser.getPassword().equals(passwordLogin)) {
					BankLog.warn("Incorrect password for username.");
					System.out.println("You have entered the incorrect password, try again.");
					break;
				} else if (currentUser.isApproved() != true) {
					BankLog.warn("User not approved");
					System.out.println("Your account has not been approved yet, please try again later.");
					break;
				}

				login(currentUser, sc);

				break;
			case 2:
				System.out.println("Enter your username:");
				String usernameRegister = sc.nextLine();
				System.out.println("Enter your password:");
				String passwordRegister = sc.nextLine();
				System.out.println();
				System.out.println();
				if (service.register(usernameRegister, passwordRegister)) {
					BankLog.warn("User registration successful.");
					System.out.println("Your account has succesfully been registered.");
					System.out.println("An employee/admin will verify your account shortly.");
				} else {
					BankLog.warn("User registration failed.");
					System.out.println("Your account has not been registered.");
					System.out.println("An error occured, please try again.");
				}
				System.out.println();
				System.out.println();
				break;
			case 3:
				BankLog.info("Program closed.");
				System.out.println("Thank you! Have a great day!");
				running = false;
				sc.close();
				break;
			default:
				BankLog.warn("User inputed an invalid menu option.");
				System.out.println("You have entered and invalid option.");
				System.out.println("Please enter a 1, 2, or 3 next time.");
				System.out.println();
				System.out.println();
				break;
			}
		} while (running);

	}

	private void login(User currentUser, Scanner sc) {
		BankLog.info("Logging in user.");
		AccountDAO accountDatabase = new AccountDaoImpl();
		TransferDAO transferDatabase = new TransferDaoImpl();
		AccountTypeDAO typeDatabase = new AccountTypeDaoImpl();
		UserDAO userDatabase = new UserDaoImpl();
		UserTypeDAO userTypeDatabase = new UserTypeDaoImpl();

		System.out.println();
		System.out.println();

		switch (currentUser.getType()) {
		case 1:
			BankLog.info("Logging in customer");
			CustomerService customerService = new CustomerServiceImpl(accountDatabase, transferDatabase, typeDatabase,
					userDatabase, userTypeDatabase);
			UserMenu customerMenu = new CustomerMenu(customerService);
			customerMenu.display(currentUser);
			break;
		case 2:
			System.out.println("Would you like to sign in as a (1)customer or (2)employee?");
			int employeeMenuChoice = Integer.parseInt(sc.nextLine());
			System.out.println();
			System.out.println();
			switch (employeeMenuChoice) {
			case 1:
				BankLog.info("Logging in employee as customer.");
				CustomerService customerServiceForEmployee = new CustomerServiceImpl(accountDatabase, transferDatabase, typeDatabase,
						userDatabase, userTypeDatabase);
				UserMenu customerMenuForEmployee = new CustomerMenu(customerServiceForEmployee);
				customerMenuForEmployee.display(currentUser);
				break;
			case 2:
				BankLog.info("Logging in employee.");
				EmployeeService employeeService = new EmployeeServiceImpl(accountDatabase, transferDatabase, typeDatabase,
						userDatabase, userTypeDatabase);
				UserMenu employeeMenu = new EmployeeMenu(employeeService);
				employeeMenu.display(currentUser);
				break;
			default:
				BankLog.warn("Invalid employee login option.");
				System.out.println("Invalid login option.");
				break;
			}
			break;
		case 3:
			System.out.println("Would you like to sign in as a (1)customer, (2)employee, or (3)admin?");
			int adminMenuChoice = Integer.parseInt(sc.nextLine());
			System.out.println();
			System.out.println();
			switch (adminMenuChoice) {
			case 1:
				BankLog.info("Logging in admin as customer.");
				CustomerService customerServiceForAdmin = new CustomerServiceImpl(accountDatabase, transferDatabase, typeDatabase,
						userDatabase, userTypeDatabase);
				UserMenu customerMenuForAdmin = new CustomerMenu(customerServiceForAdmin);
				customerMenuForAdmin.display(currentUser);
				break;
			case 2:
				BankLog.info("Logging in admin as employee.");
				EmployeeService employeeService = new EmployeeServiceImpl(accountDatabase, transferDatabase, typeDatabase,
						userDatabase, userTypeDatabase);
				UserMenu employeeMenu = new EmployeeMenu(employeeService);
				employeeMenu.display(currentUser);
				break;
			case 3:
				BankLog.info("Logging in admin.");
				AdminService adminService = new AdminServiceImpl(accountDatabase, transferDatabase, typeDatabase,
						userDatabase, userTypeDatabase);
				UserMenu adminMenu = new AdminMenu(adminService);
				adminMenu.display(currentUser);
				break;
			default:
				BankLog.warn("Invalid admin login option.");
				System.out.println("Invalid login option.");
				break;
			}
			break;
		default:
			BankLog.warn("Invalid option for welcome menu.");
			System.out.println("Something went wrong!");
			break;
		}
		
	}

	@Override
	public void options() {

		System.out.println("********************");
		System.out.println("****  Welcome!  ****");
		System.out.println("********************");
		System.out.println();
		System.out.println("Would you like to: ");
		System.out.println("(1) Login");
		System.out.println("(2) Register");
		System.out.println("(3) Quit");

	}

}
