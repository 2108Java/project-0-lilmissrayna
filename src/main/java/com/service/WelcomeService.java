package com.service;

import com.models.User;

public interface WelcomeService {
	boolean register(String username, String password);
	User login(String username);
}