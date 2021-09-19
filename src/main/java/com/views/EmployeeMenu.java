package com.views;

import com.models.User;
import com.service.EmployeeService;

public class EmployeeMenu implements UserMenu {

	EmployeeService employeeService;
	
	public EmployeeMenu(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public void options() {
		// TODO Auto-generated method stub

	}

	public void display(User user) {
		// TODO Auto-generated method stub
		
	}

}
