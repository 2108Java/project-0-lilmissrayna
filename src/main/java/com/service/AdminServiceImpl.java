package com.service;

import com.repos.UserDAO;
import com.repos.AccountDAO;
import com.repos.AccountTypeDAO;
import com.repos.TransferDAO;

public class AdminServiceImpl extends EmployeeServiceImpl implements AdminService {

	AccountDAO accountDatabase;
	TransferDAO transferDatabase;
	AccountTypeDAO typeDatabase;
	UserDAO userDatabase;

	public AdminServiceImpl(AccountDAO accountDatabase,TransferDAO transferDatabase, AccountTypeDAO typeDatabase, UserDAO userDatabase) {
		super(accountDatabase, transferDatabase, typeDatabase, userDatabase);
	}

	@Override
	public boolean addUser(String username, String password, int userType, boolean approved) {
		// TODO Auto-generated method stub
		return userDatabase.insertUser(username, password, userType, approved);
	}

	@Override
	public boolean deleteUser(String username) {
		// TODO Auto-generated method stub
		return userDatabase.deleteUser(username);
	}

	@Override
	public boolean updateUsername(String oldUser, String newName) {
		// TODO Auto-generated method stub
		return userDatabase.updateUsername(oldUser, newName);
	}

	@Override
	public boolean updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		return userDatabase.updatePassword(username, password);
	}

	@Override
	public boolean updateUserType(String username, int type) {
		// TODO Auto-generated method stub
		return userDatabase.updateType(username, type);
	}

	@Override
	public boolean deleteAccount(int id) {
		// TODO Auto-generated method stub
		return accountDatabase.deleteAccount(id);
	}

	
	
}
