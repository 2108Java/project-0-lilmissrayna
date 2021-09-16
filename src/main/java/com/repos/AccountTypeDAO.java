package com.repos;

import java.util.ArrayList;

import com.models.AccountType;

public interface AccountTypeDAO {
	AccountType selectAccountType();
	ArrayList<AccountType> selectAllAccountTypes();
	boolean updateAccountType(AccountType type);
	boolean insertAccountType(AccountType type);
	boolean deleteAccountType();
}
