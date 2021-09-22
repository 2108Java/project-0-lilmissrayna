package com.repos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.models.Transfer;

public class TransferDaoImpl implements TransferDAO {

	private static final Logger BankLog = Logger.getLogger(TransferDaoImpl.class);
	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";
	
	@Override
	public ArrayList<Transfer> selectAllTransfers(int receiver) {
		BankLog.info("Selecting all transfers.");
		ArrayList<Transfer> allTransfers = new ArrayList<Transfer>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "select * from transfers where receiving = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, receiver);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				allTransfers.add(new Transfer(rs.getInt("id"), 
							rs.getInt("sending"), 
							rs.getInt("receiving"),
							rs.getDouble("amount")));
			}
			BankLog.info("Successfully selected all transfers.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return allTransfers;
	}
	@Override
	public ArrayList<Transfer> selectTransfer(int sender, int receiver) {
		BankLog.info("Selecting transfer.");
		ArrayList<Transfer> transfersFromOne = new ArrayList<Transfer>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "select * from transfers where sending = ? and receiving = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, sender);
			ps.setInt(2, receiver);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				transfersFromOne.add(new Transfer(rs.getInt("id"), 
							rs.getInt("sending"), 
							rs.getInt("receiving"),
							rs.getInt("balance")));
			}
			BankLog.info("Successfully selected transfer.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return transfersFromOne;
	}
	@Override
	public boolean insertTransfer(int sender, int receiver, BigDecimal amount) {
		BankLog.info("Inserting transfer.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "insert into transfers(sending,receiving,amount) values(?,?,?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, sender);
			ps.setInt(2, receiver);
			ps.setBigDecimal(3, amount);
			
			success = ps.execute();
			BankLog.info("Successfully inserted transfer.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}
	
	@Override
	public boolean deleteTransfer(int id) {
		BankLog.info("Deleting transfer.");
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from transfers where id = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			success = ps.execute();
			BankLog.info("Successfully deleted transfer.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return success;
	}
	@Override
	public ArrayList<Transfer> selectAlltransfersTotal() {
		BankLog.info("Selecting all transfers in system.");
		ArrayList<Transfer> transfersPending = new ArrayList<Transfer>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "select * from transfers;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
	
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				transfersPending.add(new Transfer(rs.getInt("id"), 
							rs.getInt("sending"), 
							rs.getInt("receiving"),
							rs.getDouble("amount")));
			}
			BankLog.info("Successfully selected all transfers in system.");
		} catch (SQLException e) {
			BankLog.warn(e.toString());
			e.printStackTrace();
		}		
		return transfersPending;
	}
	
	

	

}
