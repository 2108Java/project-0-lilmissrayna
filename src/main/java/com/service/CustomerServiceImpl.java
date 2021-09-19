package com.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import com.models.Account;
import com.models.AccountType;
import com.models.Transfer;
import com.models.User;
import com.repos.AccountDAO;
import com.repos.AccountTypeDAO;
import com.repos.TransferDAO;
import com.repos.UserDAO;

public class CustomerServiceImpl implements CustomerService {

	AccountDAO accountDatabase;
	TransferDAO transferDatabase;
	AccountTypeDAO typeDatabase;
	UserDAO userDatabase;
	
	public CustomerServiceImpl(AccountDAO accountDatabase, TransferDAO transferDatabase, AccountTypeDAO typeDatabase, UserDAO userDatabase) {
		this.accountDatabase = accountDatabase;
		this.transferDatabase = transferDatabase;
		this.typeDatabase = typeDatabase;	
		this.userDatabase = userDatabase;
	}

	@Override
	public boolean applyForChecking(int userId, BigDecimal balance) {
		return accountDatabase.insertAccount(userId, 1, balance);
	}

	@Override
	public boolean applyForSavings(int userId, BigDecimal balance) {
		return accountDatabase.insertAccount(userId, 2, balance);
	}

	@Override
	public boolean applyForJoint(int userId, int secondUserId, BigDecimal balance) {
		return accountDatabase.insertAccount(userId, secondUserId, 3, balance);
	}

	@Override
	public boolean makeDeposit(int userId, int type, BigDecimal amount) {
		return accountDatabase.updateBalance(userId, type, amount);
	}

	@Override
	public boolean makeTransfer(int userId, int secondUserId, BigDecimal amount) {
		// TODO Auto-generated method stub
		return transferDatabase.insertTransfer(userId, secondUserId, amount);
	}

	@Override
	public ArrayList<Transfer> checkforTransfer(int userId) {
		return transferDatabase.selectAllTransfers(userId);
	}

	@Override
	public ArrayList<Account> viewAllBalance(int userId) {
		return accountDatabase.selectAllAccounts(userId);
	}

	@Override
	public Account viewBalance(int userId, int type) {
		return accountDatabase.selectAccount(userId, type);
	}

	@Override
	public boolean makeWithdrawl(int userId, int type, BigDecimal amount) {
		return accountDatabase.updateBalance(userId, type, amount);
	}

	@Override
	public AccountType getAcountName(int type) {
		return typeDatabase.selectAccountType(type);
	}
	
	@Override
	public User getUserId(String username) {
		// TODO Auto-generated method stub
		return userDatabase.selectUser(username);
	}

	@Override
	public ArrayList<Account> viewJointBalance(int id) {
		// TODO Auto-generated method stub
		return accountDatabase.
	}

}
