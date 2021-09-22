package com.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.models.User;

public class UserDaoImpl implements UserDAO {
	private static final Logger BankLog = Logger.getLogger(UserDaoImpl.class);
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";


	public User selectUser(String userUsername) {
		BankLog.info("Selecting user by username.");
		User selectedUser = null;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM users where username = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, userUsername);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			selectedUser = new User(rs.getInt("id"), 
							rs.getString("username"), 
							rs.getString("user_password"),
							rs.getInt("user_type"),
							rs.getBoolean("approved"));	
			}
			BankLog.info("Successfully selected user by username.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}
			return selectedUser;
	}

	@Override
	public boolean updateType(String userUsername, int type) {
		BankLog.info("Updating user type.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update users set user_type = ? where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, type);
			ps.setString(2, userUsername);
			
			ps.execute();
			success = true;
			BankLog.info("Successfully updated user type.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean updateUsername(String oldUsername, String newUsername) {
		BankLog.info("Updating username.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update users set username = ? where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, newUsername);
			ps.setString(2, oldUsername);
			ps.execute();
			success = true;
			BankLog.info("Successfully updated username.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean updatePassword(String userUsername, String newPassword) {
		BankLog.info("Updating user password.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update users set user_password = ? where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, newPassword);
			ps.setString(2, userUsername);
			ps.execute();
			success = true;
			BankLog.info("Successfully updated user password.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean deleteUser(String userUsername) {
		BankLog.info("Deleting user.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from users where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, userUsername);
			ps.execute();
			success = true;
			BankLog.info("Successfully deleted user.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean insertUser(String userUsername, String userPassword) {
		BankLog.info("Inserting user.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "insert into users(username,user_password,user_type,approved)"
						+ "values(?,?,?,?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, userUsername);
			ps.setString(2, userPassword);
			ps.setInt(3, 1);
			ps.setBoolean(4, false);
			
			ps.execute();
			
			success = true;
			BankLog.info("Successfully inserted user.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean updateApproval(String userUsername, boolean approval) {
		BankLog.info("Update user aproval.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update users set approved = ? where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setBoolean(1, approval);
			ps.setString(2, userUsername);
			
			ps.execute();
			success = true;
			BankLog.info("Succesfully updated user approval.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
		
	}

	@Override
	public boolean insertUser(String userUsername, String userPassword, int userType, boolean approved) {
		BankLog.info("Inserting user as admin.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "insert into users(username,user_password,user_type,approved)"
						+ "values(?,?,?,?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, userUsername);
			ps.setString(2, userPassword);
			ps.setInt(3, userType);
			ps.setBoolean(4, approved);
			
			ps.execute();
			
			success = true;
			BankLog.info("Successfully inserted user.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public User selectUserByAccountId(int accountOne) {
		BankLog.info("Selecting user by account id.");
		User selectedUser = null;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM users where id = (select user_one from accounts where id = ?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, accountOne);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			selectedUser = new User(rs.getInt("id"), 
							rs.getString("username"), 
							rs.getString("user_password"),
							rs.getInt("user_type"),
							rs.getBoolean("approved"));	
			}
			BankLog.info("Successfully selected user by account id.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}
			return selectedUser;
	}
	
	public User selectUserById(int id) {
		BankLog.info("Selecting user by user id.");
		User selectedUser = null;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM users where id = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			selectedUser = new User(rs.getInt("id"), 
							rs.getString("username"), 
							rs.getString("user_password"),
							rs.getInt("user_type"),
							rs.getBoolean("approved"));	
			}
			BankLog.info("Successfully selected user by user id.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}
			return selectedUser;
	}

	@Override
	public ArrayList<User> selectAllUsers() {
		BankLog.info("Selecting all users in system.");
		ArrayList<User> allUsers = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM users;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				allUsers.add(new User(rs.getInt("id"), 
							rs.getString("username"), 
							rs.getString("user_password"),
							rs.getInt("user_type"),
							rs.getBoolean("approved")));	
			}
			BankLog.info("Successfully selected all users in system.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}
			return allUsers;
	}
	

}
