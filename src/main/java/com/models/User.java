package com.models;

public class User {
	private int id;
	private String username;
	private String password;
	private int type;
	private boolean approved;
	
	public User(){
		super();
	}
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public User(int id, String username, String password){
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public User(int id, String username, String password, int type, boolean approved){
		this.id = id;
		this.username = username;
		this.password = password;
		this.type = type;
		this.approved = approved;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	
}
