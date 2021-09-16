package com.service;

import com.models.Account;

public class CustomerServiceImpl implements CustomerService {

	Account database;
	
	public CustomerServiceImpl(Account database) {
		this.database = database;
	}
	
	@Override
	public boolean applyForChecking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean applyForSavings() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean applyForJoint() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean makeDeposit(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean makeWithdrawl(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean makeTransfer(double amount, Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkforTransfer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double viewBalance(Account account) {
		return database.getBalance();

	}

}
