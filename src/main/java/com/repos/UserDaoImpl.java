package com.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.User;

public class UserDaoImpl implements UserDAO {
	
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";


	public User selectUser(String userUsername) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return selectedUser;
	}

	@Override
	public boolean updateType(String userUsername, int type) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update users set user_type = ? where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, type);
			ps.setString(2, userUsername);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean updateUsername(String oldUsername, String newUsername) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update users set username = ? where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, newUsername);
			ps.setString(2, oldUsername);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean updatePassword(String userUsername, String newPassword) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update users set user_password = ? where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, newPassword);
			ps.setString(2, userUsername);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean deleteUser(String userUsername) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from users where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, userUsername);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean insertUser(String userUsername, String userPassword) {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean updateApproval(String userUsername, boolean approval) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update users set approved = ? where username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setBoolean(1, approval);
			ps.setString(2, userUsername);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
		
	}

	@Override
	public boolean insertUser(String userUsername, String userPassword, int userType, boolean approved) {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public User selectUserByAccountId(int accountOne) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return selectedUser;
	}
	
	public User selectUserById(int id) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return selectedUser;
	}

	@Override
	public ArrayList<User> selectAllUsers() {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return allUsers;
	}
	

}
