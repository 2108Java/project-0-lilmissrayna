package com.views;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.models.Account;
import com.models.AccountType;
import com.models.User;
import com.models.UserType;
import com.service.AdminService;

public class AdminMenu implements UserMenu {
	
	private static final Logger BankLog = Logger.getLogger(AdminMenu.class);
	AdminService service;
	
	public AdminMenu(AdminService service) {
		this.service = service;
	}

	@Override
	public void options() {
		BankLog.info("Showing options.");
		System.out.println("What would you like to do?");
		System.out.println("1)Add new user");
		System.out.println("2)Delete user");
		System.out.println("3)Update username");
		System.out.println("4)Update password");
		System.out.println("5)Update user type");
		System.out.println("6)Delete account");
		System.out.println("7)Quit");
		System.out.println();

	}

	@Override
	public void display(User user) {
		BankLog.info("Running admin display");
		System.out.println("Hello " + user.getUsername() + "!");
		boolean running = true;
		System.out.println();
		while (running) {
			options();
			Scanner sc = new Scanner(System.in);
			int choice = Integer.parseInt(sc.nextLine());
			System.out.println();

			switch (choice) {
			case 1:
				addUser(sc);
				break;
			case 2:
				deleteUser(sc);
				break;
			case 3:
				updateUsername(sc);
				break;
			case 4:
				updatePassword(sc);
				break;
			case 5:
				updateUserType(sc);
				break;
			case 6:
				deleteAccount(sc);
				break;
			case 7:
				BankLog.info("Logging out.");
				System.out.println("Thank you for working for us!");
				System.out.println();
				running = false;
				break;
			default:
				BankLog.warn("Invalid admin menu option.");
				System.out.println("Invalid input, try again!");
				System.out.println();
				break;
			}
		}
		
	}

	private void deleteAccount(Scanner sc) {
		BankLog.info("Deleting account.");
		ArrayList<Account> allAccounts = service.viewAllAccounts();
		User primary;
		User secondary;
		AccountType accountType;
		for (Account acc : allAccounts) {
			primary = service.getUserById(acc.getuserPrimary());
			secondary = service.getUserById(acc.getuserSecondary());
			accountType = service.getAcountName(acc.getType());

			System.out.println("Account ID:" + acc.getId());
			System.out.println("Primary User:" + primary.getUsername());
			System.out.println("Secondary User:" + secondary.getUsername());
			System.out.println("Account Type:" + accountType.getName());
			System.out.println("Balance: $" + acc.getBalance());

			System.out.println();
		}
		
		System.out.println("Which account would you like to delete? (Enter the account ID)");
		int deleteAccount = Integer.parseInt(sc.nextLine());
		service.deleteAccount(deleteAccount);
		System.out.println();
		System.out.println("Account Deleted!");
		System.out.println();
		
	}

	private void updateUserType(Scanner sc) {
		BankLog.info("Updating user type.");
		System.out.println("Enter old username: ");
		String userName = sc.nextLine();
		System.out.println();
		
		System.out.println("Enter new type: ");
		int newType = Integer.parseInt(sc.nextLine());
		System.out.println();
		
		service.updateUserType(userName, newType);
		
		System.out.println("Successfully added new user!");
		
	}

	private void updatePassword(Scanner sc) {
		BankLog.info("Updating user password.");
		System.out.println("Enter username: ");
		String currentName = sc.nextLine();
		System.out.println();
		
		System.out.println("Enter new password: ");
		String newPassword = sc.nextLine();
		System.out.println();
		
		service.updatePassword(currentName, newPassword);
		
		System.out.println("Successfully added new user!");
		
	}

	private void updateUsername(Scanner sc) {
		BankLog.info("Updating username.");
		System.out.println("Enter old username: ");
		String oldName = sc.nextLine();
		System.out.println("Enter new username: ");
		String newName = sc.nextLine();
		
		
		service.updateUsername(oldName, newName);
		
		System.out.println("Successfully updated username!");
		
	}

	private void deleteUser(Scanner sc) {
		BankLog.info("Deleting user.");
		ArrayList<User> allUsers = service.getAllCustomers();
		for (User userList : allUsers) {
			UserType userType = service.getUserType(userList.getType());
			System.out.println("User ID: " + userList.getId());
			System.out.println("Username: " + userList.getUsername());
			System.out.println("Password: " + userList.getPassword());
			System.out.println("User Type: " + userType.getName());
			System.out.println("Approved: " + userList.isApproved());
			System.out.println();
			System.out.println();
		}
		
		System.out.println("Which user would you like to delete? (Enter the username)");
		String deleteUser = sc.nextLine();
		service.deleteUser(deleteUser);
		
		System.out.println("User Deleted!");
		
	}

	private void addUser(Scanner sc) {
		BankLog.info("Adding user.");
		System.out.println("Enter username: ");
		String username = sc.nextLine();
		System.out.println("Enter password: ");
		String password = sc.nextLine();
		System.out.println("Enter user type: ");
		int type = Integer.parseInt(sc.nextLine());
		System.out.println("Approved: (true/false)");
		boolean approval = Boolean.parseBoolean(sc.nextLine());
		
		service.addUser(username, password, type, approval);
		
		System.out.println("Successfully added new user!");
		
	}

}