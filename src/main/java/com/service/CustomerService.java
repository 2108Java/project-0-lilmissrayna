package com.service;

import com.models.Account;

public interface CustomerService{
	
	public boolean applyForChecking();
	public boolean applyForSavings();
	public boolean applyForJoint();
	public boolean makeDeposit(double amount);
	public boolean makeWithdrawl(double amount);
	public boolean makeTransfer(double amount, Account account);
	public boolean checkforTransfer();
	public void viewBalance(Account account);
	
}
