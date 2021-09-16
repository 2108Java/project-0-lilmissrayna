package com.repos;

import java.util.ArrayList;

import com.models.User;
import com.models.UserType;

public interface UserTypeDAO {
	ArrayList<UserType> selectAllUserTypes();
	boolean updateAccountType(UserType type);
	boolean insertAccountType(UserType type);
	boolean deleteAccountType(User user);
}
