package com.repos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.models.Transfer;

public class TransferDaoImpl implements TransferDAO {

	private String dbLocation = "localhost";
	private String username = "postgres";
	private String password = "Babygirl913!";
	private String url = "jdbc:postgresql://" + dbLocation + "/postgres";
	
	@Override
	public ArrayList<Transfer> selectAllTransfers(int receiver) {
		ArrayList<Transfer> allTransfers = new ArrayList<Transfer>();
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "select * from transfers where recieving = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, receiver);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				allTransfers.add(new Transfer(rs.getInt("id"), 
							rs.getInt("user_one"), 
							rs.getInt("user_two"),
							rs.getInt("balance")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return allTransfers;
	}
	@Override
	public ArrayList<Transfer> selectTransfer(int sender, int receiver) {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return transfersFromOne;
	}
	@Override
	public boolean insertTransfer(int sender, int receiver, BigDecimal amount) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "insert into transfers(sending,receiving,balance) values(?,?,?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, sender);
			ps.setInt(2, receiver);
			ps.setInt(2, receiver);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}
	@Override
	public boolean deleteAllTransfers(int receiver) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from transfers where receiving = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, receiver);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}
	@Override
	public boolean deleteTransfer(int sender, int receiver) {
		boolean success = false;
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "delete from transfers where sending = ? and receiving = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, sender);
			ps.setInt(2, receiver);
			
			success = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return success;
	}
	
	

	

}
