package com.service;

import java.util.ArrayList;

import com.models.Account;
import com.models.Transfer;
import com.repos.AccountDAO;
import com.repos.AccountTypeDAO;
import com.repos.TransferDAO;
import com.repos.UserDAO;

public class EmployeeServiceImpl extends CustomerServiceImpl implements EmployeeService{
	AccountDAO accountDatabase;
	UserDAO userDatabase;
	TransferDAO transferDatabase;
	AccountTypeDAO typeDatabase;
	
	public EmployeeServiceImpl(AccountDAO accountDatabase,TransferDAO transferDatabase, AccountTypeDAO typeDatabase, UserDAO userDatabase) {
		super(accountDatabase, transferDatabase, typeDatabase, userDatabase);
	}

	@Override
	public void viewTransactionLog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Transfer> viewTransfers(int userId) {
		return transferDatabase.selectAllTransfers(userId);
	}

	@Override
	public ArrayList<Account> viewAccount(int userId, int type) {
		return accountDatabase.selectAccount(userId, type);
	}

	@Override
	public boolean changeUserStatus(String username, boolean approval) {
		return userDatabase.updateApproval(username, approval);
	}

	
	
}
