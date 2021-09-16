package com.service;

import com.models.Account;

public interface AdminService extends EmployeeService {
	
	public boolean changeAccount(Account account);
}
