package com.views;

import com.models.Account;
import com.models.User;

public interface UserMenu{
	
	void display(User user);
	void options();
	void printAccount(Account account);
}
