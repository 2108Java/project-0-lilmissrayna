package com.views;

import java.util.Scanner;

import com.models.User;
import com.service.WelcomeService;

public class WelcomeMenuImpl implements WelcomeMenu {

	WelcomeService service;
	
	public WelcomeMenuImpl(WelcomeService service) {
		this.service = service;
	}
	
	@Override
	public void display() {
		
		UserMenu menu;
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
			} else if (passwordLogin != currentUser.getPassword()) {
				System.out.println("You have entered the incorrect password, try again.");
				break;
			} else if (currentUser.isApproved() != true) {
				System.out.println("Your account has not been approved yet, please try again later.");
				break;
			}
		
			int currentType = currentUser.getType();
			
			switch(currentType) {
			case 1:
				menu = new CustomerMenu();
				menu.display(currentUser);
				break;
			case 2:
				menu = new EmployeeMenu();
				menu.display(currentUser);
				break;
			case 3:
				menu = new AdminMenu();
				menu.display(currentUser);
				break;
			default:
				System.out.println("Something went wrong!");
				break;
			}
			
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
			break;
		default:
			System.out.println("You have entered and invalid option.");
			System.out.println("Please enter a 1, 2, or 3 next time.");
			break;
		}
		}while(running);

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
