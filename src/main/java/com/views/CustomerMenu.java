package com.views;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import com.models.Account;
import com.models.AccountType;
import com.models.User;
import com.service.CustomerService;

public class CustomerMenu implements UserMenu {

	CustomerService service;

	public CustomerMenu(CustomerService service) {
		this.service = service;
	}

	@Override
	public void options() {

		System.out.println("What would you like to do?");
		System.out.println("1) View Account");
		System.out.println("2) Apply for New Account");
		System.out.println("3) Make Deposit");
		System.out.println("4) Make Withdrawl");
		System.out.println("5) Make Transfer");
		System.out.println("6) Quit");
	}

	@Override
	public void display(User user) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Hello " + user.getUsername() + "!");

		boolean running = true;
		while (running) {
			options();

			int option = Integer.parseInt(sc.nextLine());

			switch (option) {
			case 1:
				viewAccount(user, sc);
				break;
			case 2:
				applyForAccount(user, sc);
				break;
			case 3:
				makeDeposit(user, sc);
				break;
			case 4:
				makeWithdrawl(user, sc);
				break;
			case 5:
				makeTransfer(user, sc);
				break;
			case 6:
				System.out.println("Thank you for banking with us!");
				sc.close();
				running = false;
				break;
			default:
				break;
			}
		}

	}

	private void makeTransfer(User user, Scanner sc) {
		System.out.println("Would you like to make an (1)internal or (2)external transfer?");
		int typeOfTransfer = Integer.parseInt(sc.nextLine());
		
		switch(typeOfTransfer) {
		case 1:
			break;
		case 2:
			break;
		default:
			System.out.println("");
			break;		
		}
		
	}

	private void makeWithdrawl(User user, Scanner sc) {
		System.out.println("Which account would you like to make a withdrawl from?");
		int type = Integer.parseInt(sc.nextLine());
		
		System.out.println("How much would you like to withdrawl?");
		double withdrawl = Double.parseDouble(sc.nextLine());
		
		Account account  = service.viewBalance(user.getId(), type);
		
		withdrawl = (account.getBalance() - withdrawl);
		if(withdrawl < 0) {
			System.out.println("You cannon have a negative account balance.");
			System.out.println("Please withdrawl a smaller ammount.");
		}else if(service.makeWithdrawl(user.getId(), type, BigDecimal.valueOf(withdrawl))) {
			System.out.println("New account balance is $" + withdrawl);
		}else {
			System.out.println("Somewthing went wrong!");
		}
		
	}

	private void makeDeposit(User user, Scanner sc) {
		
		System.out.println("Which account would you like to make a deposite into?");
		int type = Integer.parseInt(sc.nextLine());
		
		System.out.println("How much would you like to deposit?");
		double deposit = Double.parseDouble(sc.nextLine());
		
		Account account  = service.viewBalance(user.getId(), type);
		
		deposit += account.getBalance();
		
		if(service.makeDeposit(user.getId(), type, BigDecimal.valueOf(deposit))) {
			System.out.println("New account balance is $" + deposit);
		}else {
			System.out.println("Somewthing went wrong!");
		}
		
	}

	private void applyForAccount(User user, Scanner sc) {
		System.out.println("Which type of account would you like to apply for?");
		System.out.println("1) Checking account");
		System.out.println("2) Savings account");
		System.out.println("3) Joint account");

		int accountType = Integer.parseInt(sc.nextLine());

		System.out.println("How much would you like your initial deposit to be?");

		BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));

		switch (accountType) {
		case 1:
			boolean checking = service.applyForChecking(user.getId(), balance);

			if (!checking) {
				System.out.println("Something went wrong, try again!");
			} else {
				System.out.println("Your cheking account is all set up!");
			}

			break;
		case 2:
			boolean savings = service.applyForSavings(user.getId(), balance);

			if (!savings) {
				System.out.println("Something went wrong, try again!");
			} else {
				System.out.println("Your savings account is all set up!");
			}

			break;
		case 3:
			System.out.println("Who would you like as a second user?");
			User secondUser = service.getUserId(sc.nextLine());
			boolean joint = service.applyForJoint(user.getId(), secondUser.getId(), balance);

			if (!joint) {
				System.out.println("Something went wrong, try again!");
			} else {
				System.out.println("Your joint account is all set up!");
			}

			break;
		default:
			System.out.println("Something went wrong!");
			break;
		}

	}

	private void viewAccount(User user, Scanner sc) {
		System.out.println("Which account would you like to view?");
		System.out.println("1) View all accounts");
		System.out.println("2) View checking account");
		System.out.println("3) View savings account");
		System.out.println("4) View joint account");

		int accountType = Integer.parseInt(sc.nextLine());

		switch (accountType) {
		case 1:
			ArrayList<Account> accounts = service.viewAllBalance(user.getId());

			if (accounts.isEmpty()) {
				System.out.println("You have no accounts, register for one and try again!");
				break;
			}
			AccountType type = new AccountType();

			for (Account acc : accounts) {
				type = service.getAcountName((acc.getType()));
				System.out.println(type.getName() + ": $" + acc.getBalance());
			}

			break;

		case 2:
			ArrayList<Account> checking = service.viewBalance(user.getId(), 1);

			if (checking == null) {
				System.out.println("You have no checking accounts, register for one and try again!");
				break;
			}

			for (Account acc : checking) {
				System.out.println(service.getAcountName((acc.getId())) + ": $" + acc.getBalance());
			}
			break;

		case 3:
			ArrayList<Account> savings = service.viewBalance(user.getId(), 2);

			if (savings == null) {
				System.out.println("You have no savings accounts, register for one and try again!");
				break;
			}

			for (Account acc : savings) {
				System.out.println(service.getAcountName((acc.getId())) + ": $" + acc.getBalance());
			}
			break;

		case 4:
			ArrayList<Account> joint = service.viewJointBalance(user.getId());

			if (joint == null) {
				System.out.println("You have no joint accounts, register for one and try again!");
				break;
			}

			for (Account acc : joint) {
				System.out.println(service.getAcountName((acc.getId())) + ": $" + acc.getBalance());
			}
			break;

		default:
			System.out.println("Something went wrong!");
			break;
		}
	}
}
