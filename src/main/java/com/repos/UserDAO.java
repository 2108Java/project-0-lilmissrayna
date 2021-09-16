package com.repos;

import com.models.User;

public interface UserDAO {
	boolean updateApproval(User user);
	boolean updateType(User user);
	boolean updateUsername(User user);
	boolean updatePassword(User user);
	boolean deleteUser(int id);
	User selectUser(String userUsername);
	boolean insertUser(String username, String password);

}
