package com.service;

import com.models.Account;

public class EmployeeServiceImpl implements EmployeeService{
	Account database;
	
	public EmployeeServiceImpl(Account database) {
		this.database = database;
	}

	@Override
	public boolean changeUserStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void viewTransactionLog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

}
