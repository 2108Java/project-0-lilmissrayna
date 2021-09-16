package com.service;

import com.models.User;
import com.repos.UserDAO;

public class WelcomeServiceImpl implements WelcomeService {

	UserDAO database;
	
	public WelcomeServiceImpl(UserDAO database) {
		this.database = database;
	}
	
	@Override
	public boolean register(String username, String password) {
		return database.insertUser(username, password);
	}

	@Override
	public User login(String username) {
		return database.selectUser(username);
	}


}
