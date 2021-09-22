package com.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.models.Account;
import com.models.Transfer;
import com.models.User;
import com.models.UserType;
import com.repos.AccountDAO;
import com.repos.AccountTypeDAO;
import com.repos.TransferDAO;
import com.repos.UserDAO;
import com.repos.UserTypeDAO;

public class EmployeeServiceImpl extends CustomerServiceImpl implements EmployeeService{
	
	
	public EmployeeServiceImpl(AccountDAO accountDatabase, TransferDAO transferDatabase, AccountTypeDAO typeDatabase, UserDAO userDatabase, UserTypeDAO userTypeDatabase) {
		super(accountDatabase, transferDatabase, typeDatabase, userDatabase, userTypeDatabase);
	}

	@Override
	public void viewTransactionLog() throws IOException {
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("C:\\\\Users\\\\lilmi\\\\source\\\\repos\\\\Revature\\\\Project 0\\\\project-0-lilmissrayna\\\\src\\\\main\\\\resources\\\\banklogs.log"));
			String line = in.readLine();
			while(line != null)
			{
			  System.out.println(line);
			  line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public ArrayList<Transfer> viewTransfers(int userId) {
		return transferDatabase.selectAllTransfers(userId);
	}

	@Override
	public Account viewAccount(int userId, int type) {
		return accountDatabase.selectAccount(userId, type);
	}
	

	@Override
	public ArrayList<Account> viewAllAccounts() {
		return accountDatabase.selectAllAccounts();
	}

	@Override
	public boolean changeUserStatus(String username, boolean approval) {
		return userDatabase.updateApproval(username, approval);
	}

	@Override
	public ArrayList<User> getAllCustomers() {
		// TODO Auto-generated method stub
		return userDatabase.selectAllUsers();
	}

	@Override
	public UserType getUserType(int type) {
		// TODO Auto-generated method stub
		return userTypeDatabase.selectlUserType(type) ;
	}

	@Override
	public ArrayList<Transfer> viewAllTransfers() {
		// TODO Auto-generated method stub
		return transferDatabase.selectAlltransfersTotal();
	}

	
	
}
