package com.main;

import org.apache.log4j.Logger;

import com.repos.UserDAO;
import com.repos.UserDaoImpl;
import com.service.WelcomeService;
import com.service.WelcomeServiceImpl;
import com.views.WelcomeMenu;
import com.views.WelcomeMenuImpl;

public class MainDriver {
	private static final Logger BankLog = Logger.getLogger(MainDriver.class);
	public static void main(String[] args) {
		BankLog.info("Application Started!");
		UserDAO userDatabase = new UserDaoImpl();
		
		WelcomeService service = new WelcomeServiceImpl(userDatabase);
		
		WelcomeMenu mainMenu = new WelcomeMenuImpl(service);
		
		mainMenu.display();
		BankLog.info("Application Ended!");
	}

}
