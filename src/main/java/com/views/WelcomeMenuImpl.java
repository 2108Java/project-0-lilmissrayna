package com.views;

import java.util.Scanner;
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

	WelcomeService service;
	
	
	public WelcomeMenuImpl(WelcomeService service) {
		this.service = service;
	}
	
	@Override
	public void display() {
		
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		do {
		options();
		
		int option = Integer.parseInt(sc.nextLine());
		
		switch(option) {
		case 1:
			System.out.println("Enter your username:");
			String usernameLogin = sc.nextLine();
			System.out.println("Enter your password:");
			String passwordLogin = sc.nextLine();
			
			User currentUser = service.login(usernameLogin);
			
			
			if(currentUser == null) {
				System.out.println("User not found, please make sure you have entered the correct username.");
				break;
			} else if (!currentUser.getPassword().equals(passwordLogin)) {
				System.out.println(passwordLogin + " " + currentUser.getPassword());
				System.out.println("You have entered the incorrect password, try again.");
				break;
			} else if (currentUser.isApproved() != true) {
				System.out.println("Your account has not been approved yet, please try again later.");
				break;
			}
			
			login(currentUser);
			
			break;
		case 2:
			System.out.println("Enter your username:");
			String usernameRegister = sc.nextLine();
			System.out.println("Enter your password:");
			String passwordRegister = sc.nextLine();
			
			if(service.register(usernameRegister, passwordRegister)) {
				System.out.println("Your account has succesfully been registered.");
				System.out.println("An employee/admin will verify your account shortly.");
			}else{
				System.out.println("Your account has not been registered.");
				System.out.println("An error occured, please try again.");
			}
			break;
		case 3:
			System.out.println("Thank you! Have a great day!");
			running = false;
			sc.close();
			break;
		default:
			System.out.println("You have entered and invalid option.");
			System.out.println("Please enter a 1, 2, or 3 next time.");
			break;
		}
		}while(running);

	}

	private void login(User currentUser) {
		AccountDAO accountDatabase = new AccountDaoImpl();
		TransferDAO transferDatabase = new TransferDaoImpl();
		AccountTypeDAO typeDatabase = new AccountTypeDaoImpl();
		UserDAO userDatabase = new UserDaoImpl();
		UserTypeDAO userTypeDatabase = new UserTypeDaoImpl();
		
		switch(currentUser.getType()) {
		case 1:
			
			CustomerService customerService = new CustomerServiceImpl(accountDatabase, 
											transferDatabase, typeDatabase, userDatabase, userTypeDatabase);
			UserMenu customerMenu = new CustomerMenu(customerService);
			customerMenu.display(currentUser);
			break;
		case 2:
			EmployeeService employeeService = new EmployeeServiceImpl(accountDatabase, 
											transferDatabase, typeDatabase, userDatabase, userTypeDatabase);
			UserMenu employeeMenu = new EmployeeMenu(employeeService);
			employeeMenu.display(currentUser); 
			break;
		case 3:
			AdminService adminService = new AdminServiceImpl(accountDatabase, 
											transferDatabase, typeDatabase, userDatabase, userTypeDatabase);
			UserMenu adminMenu = new AdminMenu(adminService);
			adminMenu.display(currentUser); 
			break;
		default:
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
		System.out.println();
		System.out.println();

	}

}
