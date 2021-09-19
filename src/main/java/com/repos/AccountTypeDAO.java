package com.repos;

import java.util.ArrayList;

import com.models.AccountType;

public interface AccountTypeDAO {
	AccountType selectAccountType(int id);
	ArrayList<AccountType> selectAllAccountTypes();
	boolean updateAccountType(int id, String type);
	boolean insertAccountType(String type);
	boolean deleteAccountType(int id);
}
