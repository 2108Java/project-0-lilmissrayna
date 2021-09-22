package com.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.models.AccountType;

public class AccountTypeDaoImpl implements AccountTypeDAO {

	private static final Logger BankLog = Logger.getLogger(AccountTypeDaoImpl.class);
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";
	
	@Override
	public AccountType selectAccountType(int id) {
		BankLog.info("Selecting account type.");
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
			BankLog.info("Successfully selected account");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
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
			BankLog.warn(e.toString());
			e.printStackTrace();
		}
			return allAccountTypes;
	}
	@Override
	public boolean updateAccountType(int id, String type) {
		BankLog.info("Updating account type.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update account_types set account_type_name to ? where id = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, type);
			ps.setInt(2, id);
			
			ps.execute();
			success = true;
			BankLog.info("Successfully updated account type.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}
	@Override
	public boolean insertAccountType(String type) {
		BankLog.info("Inserting account type.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "insert into account_types(account_type_name) values(?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, type);
			
			ps.execute();
			success = true;
			BankLog.info("Successfully inserted account type.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}
	@Override
	public boolean deleteAccountType(int id) {
		BankLog.info("Deleting account type.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from account_types where id = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.execute();
			success = true;
			BankLog.info("Successfully deleted account type.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}
	
	
	
}
