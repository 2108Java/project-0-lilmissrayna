package com.main;

import com.repos.UserDAO;
import com.repos.UserDaoImpl;
import com.service.WelcomeService;
import com.service.WelcomeServiceImpl;
import com.views.WelcomeMenu;
import com.views.WelcomeMenuImpl;

public class MainDriver {

	public static void main(String[] args) {

		UserDAO database = new UserDaoImpl();
		
		WelcomeService service = new WelcomeServiceImpl(database);
		
		WelcomeMenu mainMenu = new WelcomeMenuImpl(service);
		
		mainMenu.display();
	}

}
