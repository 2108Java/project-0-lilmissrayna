package com.repos;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.models.Account;

public interface AccountDAO {
	
	ArrayList<Account> selectAllAccounts();
	Account selectAccount(int firstUser, int type);
	Account selectAccountById(int accountID);
	boolean updateBalance(int firstUser, BigDecimal amount);
	boolean insertAccount(int firstUser, int secondUser, int type, BigDecimal amount);
	boolean deleteAccount(int id);
	boolean insertAccount(int firstUser, int type, BigDecimal amount);
	ArrayList<Account> selectAllUserAccounts(int firstUser);
	
}
