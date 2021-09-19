package com.service;


public interface AdminService extends EmployeeService {
	
	boolean addUser(String username, String password, int userType, boolean approved);
	boolean deleteUser(String username);
	boolean updateUsername(String oldUser, String newName);
	boolean updatePassword(String username, String password);
	boolean updateUserType(String username, int type);
	boolean deleteAccount(int id);
	
 }
