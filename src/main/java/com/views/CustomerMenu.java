package com.views;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.models.Account;
import com.models.AccountType;
import com.models.Transfer;
import com.models.User;
import com.service.CustomerService;

public class CustomerMenu implements UserMenu {

	private static final Logger BankLog = Logger.getLogger(CustomerMenu.class);
	CustomerService service;

	public CustomerMenu(CustomerService service) {
		this.service = service;
	}

	@Override
	public void options() {
		BankLog.info("Showing options.");
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1) View Account");
		System.out.println("2) Apply for New Account");
		System.out.println("3) Make Deposit");
		System.out.println("4) Make Withdrawl");
		System.out.println("5) Make Transfer");
		System.out.println("6) Logout");

	}

	@Override
	public void display(User user) {
		BankLog.info("Customer display started");
		Scanner sc = new Scanner(System.in);

		System.out.println("Hello " + user.getUsername() + "!");
		checkForTransfer(user, sc);

		boolean running = true;
		while (running) {
			options();

			int option = Integer.parseInt(sc.nextLine());
			System.out.println();
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
				BankLog.info("Customer logging out.");
				System.out.println("Thank you for banking with us!");
				System.out.println();
				running = false;
				break;
			default:
				BankLog.warn("Invalid menu option.");
				System.out.println("Invalid input option, enter a number 1 - 6.");
				System.out.println();
				break;
			}
		}

	}

	private void checkForTransfer(User user, Scanner sc) {
		BankLog.info("Cheking for transfer on login.");
		ArrayList<Account> allAccounts = service.viewAllBalance(user.getId());
		ArrayList<Transfer> pendingTransfers = new ArrayList<>();

		for (Account acc : allAccounts) {
			pendingTransfers.addAll(service.checkforTransfer(acc.getId()));
		}

		if (pendingTransfers.isEmpty()) {
			BankLog.info("No pending transfers.");
			System.out.println("You have no pending transfers!");
			System.out.println();
		} else {
			User sender = null;
			Account current = null;
			AccountType type = null;

			for (Transfer transfer : pendingTransfers) {
				sender = service.getUserByAccountId(transfer.getAccountOne());
				current = service.getAccount(transfer.getAccountTwo());
				type = service.getAcountName(current.getType());
				System.out.println();
				System.out.println(sender.getUsername() + " would like to transfer $" + transfer.getAmount());
				System.out.println("into your " + type.getName() + " account.");
				System.out.println();
				System.out.println("Would you like to (1)accept or (2)decline?");
				int choice = Integer.parseInt(sc.nextLine());
				System.out.println();
				switch (choice) {
				case 1:
					BankLog.info("Transfer accepted.");
					BigDecimal deposit = current.getBalance();
					deposit = deposit.add(BigDecimal.valueOf(transfer.getAmount()));
					service.makeDeposit(current.getId(), deposit);

					System.out.println("Transfer was successfully accepted!");
					System.out.println();
					service.deleteTransfer(transfer.getId());

					break;
				case 2:
					BankLog.info("Transfer declined.");
					Account sending = service.getAccount(transfer.getAccountOne());
					BigDecimal reimbursment = sending.getBalance();
					reimbursment = reimbursment.add(BigDecimal.valueOf(transfer.getAmount()));
					service.makeDeposit(sending.getuserPrimary(), reimbursment);

					System.out.println("Transfer was successfully declined!");
					System.out.println();
					service.deleteTransfer(transfer.getId());
					break;
				default:
					BankLog.warn("Invalid transfer approval option.");
					System.out.println("Invalid input, please enter a 1 or 2.");
					System.out.println();
					break;
				}
			}
		}
	}

	private void makeTransfer(User user, Scanner sc) {
		BankLog.info("Making Transfer.");
		System.out.println("Would you like to make an (1)internal or (2)external transfer?");
		int typeOfTransfer = Integer.parseInt(sc.nextLine());
		System.out.println();

		System.out.println("Which account would you like to transfer from?");
		System.out.println("1) Checking account");
		System.out.println("2) Savings account");
		System.out.println("3) Joint account");
		int sending = Integer.parseInt(sc.nextLine());
		System.out.println();

		Account sender = service.viewBalance(user.getId(), sending);

		switch (typeOfTransfer) {
		case 1:
			BankLog.info("Internal transfer.");
			System.out.println("Which account would you like to transfer into?");
			System.out.println("1) Checking account");
			System.out.println("2) Savings account");
			System.out.println("3) Joint account");
			int receiving = Integer.parseInt(sc.nextLine());
			System.out.println();

			Account receiver = service.viewBalance(user.getId(), receiving);

			if (sender != null && receiver != null && !sender.equals(receiver)) {
				System.out.println("How much would you like to transfer?");
				BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
				System.out.println();

				BigDecimal withdrawl = sender.getBalance().subtract(amount);
				BigDecimal deposit = receiver.getBalance().add(amount);

				if (withdrawl.doubleValue() < 0) {// && ) {
					BankLog.warn("Internal transfer lead to negative account balance, voided.");
					System.out.println("Can not have a negative account balance, make a smaller transfer.");
				} else {
					BankLog.info("Successful internal transfer.");
					service.makeWithdrawl(sender.getId(), withdrawl);
					service.makeDeposit(receiver.getId(), deposit);
					AccountType senderType = service.getAcountName(sender.getType());
					AccountType receiverType = service.getAcountName(receiver.getType());

					System.out.println("Transfer Successful");
					System.out.println(senderType.getName() + ": $" + withdrawl);
					System.out.println(receiverType.getName() + ": $" + deposit);
					System.out.println();
				}
			}

			break;
		case 2:
			BankLog.info("External transfer");
			System.out.println("Who would you like to transfer to?");
			String receivingUserString = sc.nextLine();
			System.out.println();
			User receivingUser = service.getUserId(receivingUserString);

			System.out.println("Which account would you like to transfer into?");
			System.out.println("1) Checking account");
			System.out.println("2) Savings account");
			System.out.println("3) Joint account");
			int receivingAccountInt = Integer.parseInt(sc.nextLine());
			System.out.println();

			Account receivingAccount = service.viewBalance(receivingUser.getId(), receivingAccountInt);
			Account sendingAccount = service.viewBalance(user.getId(), sending);

			if (receivingAccount != null && sendingAccount != null && !sendingAccount.equals(receivingAccount)) {
				System.out.println("How much would you like to transfer?");
				BigDecimal transfer = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
				System.out.println();
				BigDecimal senderBalance = sendingAccount.getBalance().subtract(transfer);

				if (senderBalance.doubleValue() < 0) {// && ) {
					BankLog.warn("External transfer lead to negative account balance, voided.");
					System.out.println("Can not have a negative account balance, make a smaller transfer.");
					System.out.println();
				} else {
					BankLog.info("External transfer successful.");
					service.makeWithdrawl(sendingAccount.getId(), senderBalance);
					AccountType sendType = service.getAcountName(sendingAccount.getType());
					service.makeTransfer(sendingAccount.getId(), receivingAccount.getId(), transfer);

					System.out.println(
							"Transfer Successful, please wait for " + receivingUserString + " to accept the transfer!");
					System.out.println(sendType.getName() + ": $" + senderBalance);
					System.out.println();
				}
			}
			break;
		default:
			BankLog.warn("Invalid transfer option.");
			System.out.println("Invalid input, please enter a 1 or 2.");
			System.out.println();
			break;
		}

	}

	private void makeWithdrawl(User user, Scanner sc) {
		BankLog.info("Making withdrawl.");
		System.out.println("Which account would you like to make a withdrawl from?");
		System.out.println("1) Checking account");
		System.out.println("2) Savings account");
		System.out.println("3) Joint account");
		int type = Integer.parseInt(sc.nextLine());
		System.out.println();

		System.out.println("How much would you like to withdrawl?");
		BigDecimal withdrawl = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
		System.out.println();

		Account account = service.viewBalance(user.getId(), type);

		withdrawl = (account.getBalance().subtract(withdrawl));
		if ((withdrawl.compareTo(BigDecimal.valueOf(0))) < 0) {
			BankLog.warn("Withdrawl lead to negative account balance, voided.");
			System.out.println("You cannon have a negative account balance.");
			System.out.println("Please withdrawl a smaller ammount.");
			System.out.println();
		} else {
			BankLog.info("Successful withdrawl.");
			service.makeWithdrawl(account.getId(), withdrawl);
			System.out.println("New account balance is $" + withdrawl);
			System.out.println();
		}

	}

	private void makeDeposit(User user, Scanner sc) {
		BankLog.info("Making deposit.");
		System.out.println("Which account would you like to make a deposite into?");
		System.out.println("1) Checking account");
		System.out.println("2) Savings account");
		System.out.println("3) Joint account");
		int type = Integer.parseInt(sc.nextLine());
		System.out.println();

		System.out.println("How much would you like to deposit?");
		BigDecimal deposit = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
		System.out.println();

		Account account = service.viewBalance(user.getId(), type);
		if (deposit.compareTo(BigDecimal.valueOf(0)) < 0) {
			System.out.println("Cannot deposit a negative amount.");
			System.out.println();
			BankLog.info("Failed making deposit.");
		} else {
			BankLog.info("Succeeded making deposit.");
			deposit = deposit.add(account.getBalance());

			service.makeDeposit(account.getId(), deposit);

			System.out.println("New account balance is $" + deposit);
			System.out.println();
		}
	}

	private void applyForAccount(User user, Scanner sc) {
		BankLog.info("Applying for account.");
		System.out.println("Which type of account would you like to apply for?");
		System.out.println("1) Checking account");
		System.out.println("2) Savings account");
		System.out.println("3) Joint account");
		int accountType = Integer.parseInt(sc.nextLine());
		System.out.println();
		
		System.out.println("How much would you like your initial deposit to be?");
		BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
		System.out.println();
		
		if ((balance.compareTo(BigDecimal.valueOf(0)) < 0)) {
			BankLog.warn("User entered a negative starting balance.");
			System.out.println("You can not start an account with a negative balance.");
			System.out.println();
		} else {
			switch (accountType) {
			case 1:
				boolean checking = service.applyForChecking(user.getId(), balance);

				if (!checking) {
					BankLog.warn("Checking account application error.");
					System.out.println("Something went wrong, try again!");
					System.out.println();
				} else {
					BankLog.info("Checking account application successful.");
					System.out.println("Your cheking account is all set up!");
					System.out.println();
				}

				break;
			case 2:
				boolean savings = service.applyForSavings(user.getId(), balance);

				if (!savings) {
					BankLog.warn("Savings account application error.");
					System.out.println("Something went wrong, try again!");
					System.out.println();
				} else {
					BankLog.info("Savings account application successful.");
					System.out.println("Your savings account is all set up!");
					System.out.println();
				}

				break;
			case 3:
				System.out.println("Who would you like as a second user?");
				User secondUser = service.getUserId(sc.nextLine());
				boolean joint = service.applyForJoint(user.getId(), secondUser.getId(), balance);

				if (!joint) {
					BankLog.warn("Joint account application error.");
					System.out.println("Something went wrong, try again!");
					System.out.println();
				} else {
					BankLog.info("Joint account application successful.");
					System.out.println("Your joint account is all set up!");
					System.out.println();
				}

				break;
			default:
				BankLog.warn("Invalid acount type inputed.");
				System.out.println("Invalid acount type.");
				System.out.println();
				break;
			}
		}
	}

	private void viewAccount(User user, Scanner sc) {
		BankLog.info("Viewing accounts.");
		System.out.println("Which account would you like to view?");
		System.out.println("1) View all accounts");
		System.out.println("2) View checking account");
		System.out.println("3) View savings account");
		System.out.println("4) View joint account");
		int accountType = Integer.parseInt(sc.nextLine());
		System.out.println();
		
		switch (accountType) {
		case 1:
			ArrayList<Account> accounts = service.viewAllBalance(user.getId());

			if (accounts.isEmpty()) {
				BankLog.warn("No account found for user.");
				System.out.println("You have no accounts, register for one and try again!");
				System.out.println();
				break;
			}
			AccountType type = new AccountType();

			for (Account acc : accounts) {
				type = service.getAcountName((acc.getType()));
				System.out.println(type.getName() + ": $" + acc.getBalance());
				System.out.println();
			}
			BankLog.info("Successfully viewed all account.");
			break;

		case 2:
			Account checking = service.viewBalance(user.getId(), 1);

			if (checking == null) {
				BankLog.warn("No checking account found for user.");
				System.out.println("You have no checking accounts, register for one and try again!");
				System.out.println();
				break;
			}
			System.out.println("Checking: $" + checking.getBalance());
			System.out.println();
			BankLog.info("Successfully viewed checking account.");
			break;

		case 3:
			Account savings = service.viewBalance(user.getId(), 2);
			if (savings == null) {
				BankLog.warn("No savings account found for user.");
				System.out.println("You have no savings accounts, register for one and try again!");
				System.out.println();
				break;
			}
			System.out.println("Savings: $" + savings.getBalance());
			System.out.println();
			BankLog.info("Successfully viewed savings account.");
			break;

		case 4:
			Account joint = service.viewBalance(user.getId(), 3);

			if (joint == null) {
				BankLog.warn("No joint account found for user.");
				System.out.println("You have no joint accounts, register for one and try again!");
				System.out.println();
				break;
			}
			System.out.println("Joint : $" + joint.getBalance());
			System.out.println();
			BankLog.info("Successfully viewed joint account.");
			break;

		default:
			BankLog.warn("Invalid view account option.");
			System.out.println("Invalid account option.");
			System.out.println();
			break;
		}
	}
}
