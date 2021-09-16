package com.repos;
import java.util.ArrayList;

import com.models.Account;

public interface AccountDAO {
	
	ArrayList<Account> selectAllAccounts();
	Account selectCheckingAccount();
	Account selectSavingsAccount();
	Account selectJointAccount();
	boolean updateBalance(double amount);
	boolean insertAccount(Account account);
	boolean deleteAccount();
	
}
