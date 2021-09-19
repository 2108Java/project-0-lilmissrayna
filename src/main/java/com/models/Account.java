package com.models;

public class Account {
	private int id;
	private int userPrimary;
	private int userSecondary;
	private int type;
	private double balance;
	
	public Account(int id, int userPrimary, int userSecondary, int type, double balance){
		this.id = id;
		this.userPrimary = userPrimary;
		this.userSecondary = userSecondary;
		this.type = type;
		this.balance = balance;
	}
	
	public Account(){
		super();
	}

	public int getuserPrimary() {
		return userPrimary;
	}

	public void setuserPrimary(int userPrimary) {
		this.userPrimary = userPrimary;
	}

	public int getuserSecondary() {
		return userSecondary;
	}

	public void setuserSecondary(int userSecondary) {
		this.userSecondary = userSecondary;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

}
