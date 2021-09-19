package com.repos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.models.Account;

public class AccountDaoImpl implements AccountDAO {
	
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";

	@Override
	public ArrayList<Account> selectAllAccounts(int firstUser) {
		ArrayList<Account> allAccounts = new ArrayList<Account>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM accounts where user_one = ? or user_two = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, firstUser);
			ps.setInt(2, firstUser);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				allAccounts.add(new Account(rs.getInt("id"), 
							rs.getInt("user_one"), 
							rs.getInt("user_two"),
							rs.getInt("account_type"),
							rs.getDouble("balance")));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return allAccounts;
	}

	@SuppressWarnings("null")
	@Override
	public ArrayList<Account> selectAccount(int firstUser, int type) {
		ArrayList<Account> accountOfType = null;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM accounts where account_type = ? and (user_one = ? or user_two = ?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, type);
			ps.setInt(2, firstUser);
			ps.setInt(3, firstUser);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				accountOfType.add(new Account(rs.getInt("id"), 
							rs.getInt("user_one"), 
							rs.getInt("user_two"),
							rs.getInt("account_type"),
							rs.getDouble("balance")));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return accountOfType;
	}

	@Override
	public boolean updateBalance(int firstUser, int type, BigDecimal amount) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update accounts set balance = ? where user_one = ? and account_type = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setBigDecimal(1, amount);
			ps.setInt(2, firstUser);
			ps.setInt(3, firstUser);
			ps.setInt(4, type);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean insertAccount(int firstUser, int secondUser, int type, BigDecimal amount) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "insert into accounts(user_one, user_two, account_type, balance) "
						+ "values(?,?,?,?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, firstUser);
			ps.setInt(2, secondUser);
			ps.setInt(3, type);
			ps.setBigDecimal(4, amount);
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}
	
	@Override
	public boolean insertAccount(int firstUser, int type, BigDecimal amount) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "insert into accounts(user_one, user_two, account_type, balance) "
						+ "values(?,?,?,?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, firstUser);
			ps.setInt(2, firstUser);
			ps.setInt(3, type);
			ps.setBigDecimal(4, amount);
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean deleteAccount(int id) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from accounts where id = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.execute();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public Account selectAccountById(int accountID) {
		Account userAccount = new Account();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM accounts where id = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, accountID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userAccount = new Account(rs.getInt("id"), 
							rs.getInt("user_one"), 
							rs.getInt("user_two"),
							rs.getInt("account_type"),
							rs.getDouble("balance"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return userAccount;
	}

	
}
