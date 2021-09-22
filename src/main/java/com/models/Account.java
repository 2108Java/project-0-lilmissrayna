package com.models;

import java.math.BigDecimal;

public class Account {
	private int id;
	private int userPrimary;
	private int userSecondary;
	private int type;
	private BigDecimal balance;
	
	public Account(int id, int userPrimary, int userSecondary, int type, BigDecimal bigDecimal){
		this.id = id;
		this.userPrimary = userPrimary;
		this.userSecondary = userSecondary;
		this.type = type;
		this.balance = bigDecimal;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

}
