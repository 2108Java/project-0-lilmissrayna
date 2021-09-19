package com.service;

import java.util.ArrayList;
import com.models.Account;
import com.models.Transfer;

public interface EmployeeService extends CustomerService{
	
	public void	viewTransactionLog();
	ArrayList<Transfer> viewTransfers(int userId);
	ArrayList<Account> viewAccount(int userId, int type);
	boolean changeUserStatus(String username, boolean approval);
	
}
