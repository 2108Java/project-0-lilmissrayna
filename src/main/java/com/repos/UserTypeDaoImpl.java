package com.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.models.UserType;

public class UserTypeDaoImpl implements UserTypeDAO {
	
	private static final Logger BankLog = Logger.getLogger(UserTypeDaoImpl.class);
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";

	@Override
	public ArrayList<UserType> selectAllUserTypes() {
		BankLog.info("Selecting all user types.");
		ArrayList<UserType> allUserTypes = new ArrayList<UserType>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "select * from user_types;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				allUserTypes.add(new UserType(rs.getInt("id"), 
							rs.getString("user_type_name")));
			}
			BankLog.info("Successfully selected all user types.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return allUserTypes;
	}

	@Override
	public UserType selectlUserType(int id) {
		BankLog.info("Selecting user type by id.");
		UserType userType = new UserType();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "select * from user_types where id = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				userType = new UserType(rs.getInt("id"), 
							rs.getString("user_type_name"));
			}
			BankLog.info("Successfully selected user type by id.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return userType;
	}

	@Override
	public boolean updateAccountType(String oldType, String newType) {
		BankLog.info("Updating user type.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update user_types set user_type_name = ? where user_type_name = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, newType);
			ps.setString(2, oldType);
			
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
	public boolean insertAccountType(String type) {
		BankLog.info("Inserting user type.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "insert into user_types(user_type_name) values(?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, type);
			ps.execute();
			success = true;
			BankLog.info("Successfully inserted user type.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean deleteAccountType(int id) {
		BankLog.info("Deleting user type.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from user_types where id = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.execute();
			success = true;
			BankLog.info("Successfully deleted user type.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

}
