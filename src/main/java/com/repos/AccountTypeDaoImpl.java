package com.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.AccountType;

public class AccountTypeDaoImpl implements AccountTypeDAO {

	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";
	
	@Override
	public AccountType selectAccountType(int id) {
		AccountType accountType = null;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM account_types where id = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			accountType = new AccountType(rs.getInt("id"), 
							rs.getString("account_type_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return accountType;
	}
	@Override
	public ArrayList<AccountType> selectAllAccountTypes() {
		ArrayList<AccountType> allAccountTypes = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM account_types order by id;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				allAccountTypes.add(new AccountType(rs.getInt("id"), 
							rs.getString("type_name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return allAccountTypes;
	}
	@Override
	public boolean updateAccountType(int id, String type) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update account_types set type_name to ? where id = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, type);
			ps.setInt(2, id);
			
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
			String sql = "insert into account_types(type_name) values(?);";
			
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
			String sql = "delete from account_types where id = ?";
			
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
