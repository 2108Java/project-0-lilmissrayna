package com.service;

import java.io.IOException;
import java.util.ArrayList;
import com.models.Account;
import com.models.Transfer;
import com.models.User;
import com.models.UserType;

public interface EmployeeService extends CustomerService{
	
	public void	viewTransactionLog() throws IOException;
	ArrayList<Transfer> viewTransfers(int userId);
	Account viewAccount(int userId, int type);
	boolean changeUserStatus(String username, boolean approval);
	ArrayList<Account> viewAllAccounts();
	public ArrayList<User> getAllCustomers();
	public UserType getUserType(int type);
	public ArrayList<Transfer> viewAllTransfers();
	
}
