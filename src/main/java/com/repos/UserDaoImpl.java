package com.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.models.User;

public class UserDaoImpl implements UserDAO {
	
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";


	public User selectUser(String userUsername, String userPassword) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			System.out.println("You made it!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	}

	public User selectUser(String userUsername) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			System.out.println("You made it!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	}
	
	@Override
	public boolean updateApproval(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateType(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUsername(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertUser(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
