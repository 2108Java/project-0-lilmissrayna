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
import com.repos.UserTypeDAO;

public class CustomerServiceImpl implements CustomerService {

	AccountDAO accountDatabase;
	TransferDAO transferDatabase;
	AccountTypeDAO typeDatabase;
	UserDAO userDatabase;
	UserTypeDAO userTypeDatabase;
	
	public CustomerServiceImpl(AccountDAO accountDatabase, TransferDAO transferDatabase, AccountTypeDAO typeDatabase, UserDAO userDatabase, UserTypeDAO userTypeDatabase) {
		this.accountDatabase = accountDatabase;
		this.transferDatabase = transferDatabase;
		this.typeDatabase = typeDatabase;	
		this.userDatabase = userDatabase;
		this.userTypeDatabase = userTypeDatabase;
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
	public boolean makeDeposit(int userId, BigDecimal amount) {
		return accountDatabase.updateBalance(userId, amount);
	}

	@Override
	public boolean makeTransfer(int userId, int secondUserId, BigDecimal amount) {
		return transferDatabase.insertTransfer(userId, secondUserId, amount);
	}

	@Override
	public ArrayList<Transfer> checkforTransfer(int userId) {
		return transferDatabase.selectAllTransfers(userId);
	}

	@Override
	public ArrayList<Account> viewAllBalance(int userId) {
		return accountDatabase.selectAllUserAccounts(userId);
	}

	@Override
	public Account viewBalance(int userId, int type) {
		return accountDatabase.selectAccount(userId, type);
	}

	@Override
	public boolean makeWithdrawl(int userId, BigDecimal amount) {
		return accountDatabase.updateBalance(userId, amount);
	}

	@Override
	public AccountType getAcountName(int type) {
		return typeDatabase.selectAccountType(type);
	}
	
	@Override
	public User getUserId(String username) {
		return userDatabase.selectUser(username);
	}

	@Override
	public User getUserByAccountId(int accountOne) {
		// TODO Auto-generated method stub
		return userDatabase.selectUserByAccountId(accountOne);
	}

	@Override
	public Account getAccount(int accountTwo) {
		// TODO Auto-generated method stub
		return accountDatabase.selectAccountById(accountTwo);
	}

	@Override
	public boolean deleteTransfer(int id) {
		// TODO Auto-generated method stub
		return transferDatabase.deleteTransfer(id);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDatabase.selectUserById(id);
	}

}
