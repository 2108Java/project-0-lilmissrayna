package com.service;

import com.models.Account;

public class AdminServiceImpl implements AdminService {

	Account database;
	
	public AdminServiceImpl(Account database) {
		this.database = database;
	}

	@Override
	public boolean changeAccountStatus(Account account) {
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

	@Override
	public boolean changeAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

}
