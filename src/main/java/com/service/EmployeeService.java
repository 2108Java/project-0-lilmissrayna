package com.service;

import com.models.Account;

public interface EmployeeService{
	
	public boolean changeUserStatus(String username);
	public void	viewTransactionLog();
	public void viewAccount(String username);
	
}
