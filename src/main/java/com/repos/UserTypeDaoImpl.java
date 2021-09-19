package com.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.models.UserType;

public class UserTypeDaoImpl implements UserTypeDAO {
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";

	@Override
	public ArrayList<UserType> selectAllUserTypes() {
		ArrayList<UserType> allUserTypes = new ArrayList<UserType>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "select * from user_types;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				allUserTypes.add(new UserType(rs.getInt("id"), 
							rs.getString("user_type_name")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return allUserTypes;
	}

	@Override
	public UserType selectlUserType(int id) {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return userType;
	}

	@Override
	public boolean updateAccountType(String oldType, String newType) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update account_type set account_type_name = ? where account_type_name = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, newType);
			ps.setString(2, oldType);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean insertAccountType(String type) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "insert int account_type(account_type_name) values (?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, type);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean deleteAccountType(int id) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from account_type where id = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

}
