package com.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.models.Account;
import com.models.AccountType;
import com.models.Transfer;
import com.models.User;

public interface CustomerService{
	
	public boolean applyForChecking(int userId, BigDecimal balance);
	public boolean applyForSavings(int userId, BigDecimal balance);
	public boolean applyForJoint(int userId, int secondUserId, BigDecimal balance);
	public boolean makeDeposit(int userId, int type, BigDecimal amount);
	public boolean makeTransfer(int userId, int secondUserId, BigDecimal amount);
	public ArrayList<Transfer> checkforTransfer(int userId);
	public ArrayList<Account> viewAllBalance(int userId);
	public Account viewBalance(int userId, int type);
	public boolean makeWithdrawl(int userId, int type, BigDecimal amount);
	public AccountType getAcountName(int type);
	public User getUserId(String username);
	public ArrayList<Account> viewJointBalance(int id);
	
}
