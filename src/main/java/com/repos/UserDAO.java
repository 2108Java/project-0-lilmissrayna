package com.repos;

import com.models.User;

public interface UserDAO {
	boolean updateType(String username, int type);
	boolean updateUsername(String oldUsername, String newUsername);
	boolean updatePassword(String username, String password);
	boolean deleteUser(String userUsername);
	User selectUser(String userUsername);
	boolean insertUser(String username, String password);
	boolean updateApproval(String username, boolean approval);
	boolean insertUser(String username, String password, int userType, boolean approved);
	
	
}
