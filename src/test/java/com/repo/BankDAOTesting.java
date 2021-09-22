package com.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import com.repos.AccountDAO;
import com.repos.AccountDaoImpl;
import com.repos.AccountTypeDAO;
import com.repos.AccountTypeDaoImpl;
import com.repos.TransferDAO;
import com.repos.TransferDaoImpl;
import com.repos.UserDAO;
import com.repos.UserDaoImpl;
import com.repos.UserTypeDAO;
import com.repos.UserTypeDaoImpl;

class BankDAOTesting {
	AccountDAO accountDatabase = new AccountDaoImpl();
	TransferDAO transferDatabase = new TransferDaoImpl();
	AccountTypeDAO typeDatabase = new AccountTypeDaoImpl();
	UserTypeDAO userTypeDatabase = new UserTypeDaoImpl();
	UserDAO userDatabase = new UserDaoImpl();
	

	@Test
	void testUserDatabase() {
		
		assertEquals(true, userDatabase.insertUser("test3", "123"));
		assertEquals(true, userDatabase.insertUser("test4", "123", 1, true));
		assertEquals(true, userDatabase.updateApproval("test1", true));
		assertNotNull(userDatabase.selectAllUsers());
		assertNotNull(userDatabase.selectUser("customer"));
		assertNotNull(userDatabase.selectUserByAccountId(1));
		assertNotNull(userDatabase.selectUserById(2));
		assertEquals(true, userDatabase.deleteUser("test3"));
		assertEquals(true, userDatabase.deleteUser("test4"));
		
	}
	
	@Test
	void testUserTypeDatabase() {
		assertEquals(true ,userTypeDatabase.insertAccountType("test123"));
		assertEquals(true ,userTypeDatabase.deleteAccountType(4));
	}
	
	@Test
	void testAccountDatabase() {
		assertNotNull(accountDatabase.selectAllAccounts());
		assertNotNull(accountDatabase.selectAccount(2,1));
	}
	
	@Test
	void testAccountTypeDatabase() {
		assertEquals(true , typeDatabase.insertAccountType("test123"));
		assertEquals(true , typeDatabase.deleteAccountType(4));
	}
	
	@Test
	void testTransferDatabase() {
		assertNotNull(transferDatabase.selectAlltransfersTotal());
		assertNull(transferDatabase.selectAllTransfers(56));
	}

}
