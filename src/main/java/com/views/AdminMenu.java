package com.views;

import com.models.User;
import com.service.AdminService;

public class AdminMenu implements UserMenu {

	AdminService adminService;
	
	public AdminMenu(AdminService adminService) {
		this.adminService = adminService;
	}

	@Override
	public void options() {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(User user) {
		// TODO Auto-generated method stub
		
	}

}
