package com.repos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.models.Account;

public class AccountDaoImpl implements AccountDAO {
	private static final Logger BankLog = Logger.getLogger(AccountDaoImpl.class);
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";

	@Override
	public ArrayList<Account> selectAllUserAccounts(int firstUser) {
		BankLog.info("Selecting all user accounts.");
		ArrayList<Account> allAccounts = new ArrayList<Account>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM accounts where user_one = ? or user_two = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, firstUser);
			ps.setInt(2, firstUser);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				try {
					allAccounts.add(new Account(rs.getInt("id"), 
								rs.getInt("user_one"), 
								rs.getInt("user_two"),
								rs.getInt("account_type"),
								BigDecimal.valueOf(DecimalFormat.getCurrencyInstance().parse(rs.getString("balance")).doubleValue())));
					BankLog.info("Added account to allAccounts.");
				} catch (ParseException e) {
					BankLog.warn(e.toString());
					e.printStackTrace();
				}	
			}
			BankLog.info("All accounts added!");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}
			return allAccounts;
	}

	@Override
	public Account selectAccount(int firstUser, int type) {
		BankLog.info("Selecting account.");
		Account accountOfType = null;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM accounts where account_type = ? and (user_one = ? or user_two = ?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, type);
			ps.setInt(2, firstUser);
			ps.setInt(3, firstUser);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				try {
					accountOfType=new Account(rs.getInt("id"), 
							rs.getInt("user_one"), 
							rs.getInt("user_two"),
							rs.getInt("account_type"),
							BigDecimal.valueOf(DecimalFormat.getCurrencyInstance().parse(rs.getString("balance")).doubleValue()));
					BankLog.info("Account " + accountOfType.toString() + " added.");
				} catch (ParseException e) {
					BankLog.warn(e.toString());
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}
			return accountOfType;
	}

	@Override
	public boolean updateBalance(int id, BigDecimal amount) {
		BankLog.info("Updating balance.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "update accounts set balance = ? where id = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setBigDecimal(1, amount);
			ps.setInt(2, id);
			
			success = ps.execute();
			BankLog.info("Balance updated to $" + amount);
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean insertAccount(int firstUser, int secondUser, int type, BigDecimal amount) {
		BankLog.info("Inserting Joint account.");
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
			BankLog.info("Account successfully created.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}
	
	@Override
	public boolean insertAccount(int firstUser, int type, BigDecimal amount) {
		BankLog.info("Inserting Checking/Savings account.");
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
			BankLog.info("Account successfully created.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public boolean deleteAccount(int id) {
		BankLog.info("Deleting account.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from accounts where id = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.execute();
			
			success = true;
			BankLog.info("Successfully deleted account.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}

	@Override
	public Account selectAccountById(int accountID) {
		BankLog.info("Selecting account by ID.");
		Account userAccount = new Account();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM accounts where id = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, accountID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				try {
					userAccount = new Account(rs.getInt("id"), 
							rs.getInt("user_one"), 
							rs.getInt("user_two"),
							rs.getInt("account_type"),
							BigDecimal.valueOf(DecimalFormat.getCurrencyInstance().parse(rs.getString("balance")).doubleValue()));
				} catch (ParseException e) {
					BankLog.warn(e.toString());
					e.printStackTrace();
				}	
			}
			BankLog.info("Successfully retrieved account.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}
			return userAccount;
	}

	@Override
	public ArrayList<Account> selectAllAccounts() {
		BankLog.info("Selecting all accounts.");
		ArrayList<Account> accounts = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM accounts;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				try {
					accounts.add(new Account(rs.getInt("id"), 
								rs.getInt("user_one"), 
								rs.getInt("user_two"),
								rs.getInt("account_type"),
								BigDecimal.valueOf(DecimalFormat.getCurrencyInstance().parse(rs.getString("balance")).doubleValue())));
				} catch (ParseException e) {
					BankLog.warn(e.toString());
					e.printStackTrace();
				}	
			}
			BankLog.info("Successfully selected all accounts.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}
			return accounts;
	}

	
}
