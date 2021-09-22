package com.views;

import java.util.ArrayList;
import java.util.Scanner;

import com.models.Account;
import com.models.AccountType;
import com.models.Transfer;
import com.models.User;
import com.models.UserType;
import com.service.EmployeeService;

public class EmployeeMenu implements UserMenu {

	EmployeeService service;

	public EmployeeMenu(EmployeeService service) {
		this.service = service;
	}

	@Override
	public void options() {
		System.out.println("What would you like to do?");
		System.out.println("1)View all accounts");
		System.out.println("2)View individual account");
		System.out.println("3)View all users");
		System.out.println("4)View individual user");
		System.out.println("5)Update user status");
		System.out.println("6)View pending transfers");
		System.out.println("7)View transaction log");
		System.out.println("8)Quit");

	}

	public void display(User user) {
		System.out.println("Hello " + user.getUsername() + "!");
		boolean running = true;

		while (running) {
			options();
			Scanner sc = new Scanner(System.in);
			int choice = Integer.parseInt(sc.nextLine());
			System.out.println();
			System.out.println();

			switch (choice) {
			case 1:
				viewAllAccounts();
				break;
			case 2:
				viewAccount(sc);
				break;
			case 3:
				viewAllUsers();
				break;
			case 4:
				viewUser(sc);
				break;
			case 5:
				updateApproval(sc);
				break;
			case 6:
				viewTransfers();
				break;
			case 7:
				service.viewTransactionLog();
				break;
			case 8:
				System.out.println("Thank you for working for us!");
				running = false;
				break;
			default:
				System.out.println("Invalid input, try again!");
				break;
			}
		}

	}

	private void viewTransfers() {
		ArrayList<Transfer> allTransfers = service.viewAllTransfers();
		for (Transfer trans : allTransfers) {
			User sendingUser = service.getUserByAccountId(trans.getAccountOne());
			User receivingUser = service.getUserByAccountId(trans.getAccountTwo());
			Account sendingAccount = service.getAccount(trans.getAccountOne());
			Account receivingAccount = service.getAccount(trans.getAccountOne());
			AccountType sendingType = service.getAcountName(sendingAccount.getType());
			AccountType receivingType = service.getAcountName(receivingAccount.getType());

			System.out.println("Transfer ID: " + trans.getId());
			System.out.println("Sender Username: " + sendingUser.getUsername());
			System.out.println("Sender Account: " + sendingType.getName());
			System.out.println("Receiver Username: " + receivingUser.getUsername());
			System.out.println("Receiver Account: " + receivingType.getName());
			System.out.println("Ammount: " + trans.getAmount());
			System.out.println();
			System.out.println();
		}

	}

	private void updateApproval(Scanner sc) {
		System.out.println("Enter the username of the user you would like to update: ");
		User editUser = service.getUserId(sc.nextLine());
		System.out.println("Enter true/false for their approval status:");
		boolean approval = Boolean.parseBoolean(sc.nextLine());

		if (editUser == null) {
			System.out.println("User doesnt exist, please enter an existing user!");
		} else {
			service.changeUserStatus(editUser.getUsername(), approval);
			System.out.println(editUser.getUsername() + "'s approval is set to " + approval);
		}
		System.out.println();
		System.out.println();
	}

	private void viewUser(Scanner sc) {
		System.out.println("Enter the username of the user you would like to view: ");
		User viewUser = service.getUserId(sc.nextLine());
		UserType viewType = service.getUserType(viewUser.getType());

		System.out.println("User ID: " + viewUser.getId());
		System.out.println("Username: " + viewUser.getUsername());
		System.out.println("Password: " + viewUser.getPassword());
		System.out.println("User Type: " + viewType.getName());
		System.out.println("Approved: " + viewUser.isApproved());
		System.out.println();
		System.out.println();
	}

	private void viewAllUsers() {
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

	}

	private void viewAccount(Scanner sc) {
		System.out.println("Which user would you like to view?");
		String userUsername = sc.nextLine();
		System.out.println("Which of thier accouts would you like to view?");
		System.out.println("1) Checking account");
		System.out.println("2) Savings account");
		System.out.println("3) Joint account");
		int accountType = Integer.parseInt(sc.nextLine());

		User user = service.getUserId(userUsername);
		Account account = service.viewAccount(user.getId(), accountType);
		AccountType type = service.getAcountName(accountType);
		if (account != null) {
			System.out.println("Account ID:" + account.getId());
			System.out.println("Primary User:" + user.getUsername());
			System.out.println("Account Type:" + type.getName());
			System.out.println("Balance: $" + account.getBalance());
		} else {
			System.out.println("Account not found, try entering another username or account type.");
		}
		System.out.println();
		System.out.println();

	}

	private void viewAllAccounts() {
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
			System.out.println();
		}

	}

}
