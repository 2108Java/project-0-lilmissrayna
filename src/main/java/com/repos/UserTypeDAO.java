package com.repos;

import java.util.ArrayList;
import com.models.UserType;

public interface UserTypeDAO {
	ArrayList<UserType> selectAllUserTypes();
	UserType selectlUserType(int id);
	boolean updateAccountType(String oldType,String newType);
	boolean insertAccountType(String type);
	boolean deleteAccountType(int id);
}
