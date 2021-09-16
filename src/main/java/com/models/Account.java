package com.models;

public class Account {
	private int id;
	private User userPrimary;
	private User userSecondary;
	private AccountType type;
	private double balance;
	
	public Account(int id, User userPrimary, User userSecondary, AccountType type, double balance){
		this.id = id;
		this.userPrimary = userPrimary;
		this.userSecondary = userSecondary;
		this.type = type;
		this.balance = balance;
	}
	
	public Account(int id, User userPrimary, AccountType type, double balance){
		this.id = id;
		this.userPrimary = userPrimary;
		this.type = type;
		this.balance = balance;
	}
	
	public Account(User userPrimary, User userSecondary, AccountType type, double balance){
		this.userPrimary = userPrimary;
		this.userSecondary = userSecondary;
		this.type = type;
		this.balance = balance;
	}
	
	public Account(User userPrimary, AccountType type, double balance){
		this.userPrimary = userPrimary;
		this.type = type;
		this.balance = balance;
	}
	
	public Account(){
		super();
	}

	public User getuserPrimary() {
		return userPrimary;
	}

	public void setuserPrimary(User userPrimary) {
		this.userPrimary = userPrimary;
	}

	public User getuserSecondary() {
		return userSecondary;
	}

	public void setuserSecondary(User userSecondary) {
		this.userSecondary = userSecondary;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
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
