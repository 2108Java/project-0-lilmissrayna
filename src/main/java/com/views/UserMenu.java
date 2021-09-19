package com.views;

import com.models.Account;
import com.models.User;

public interface UserMenu{
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";
	
	void display(User user);
	void options();
}
