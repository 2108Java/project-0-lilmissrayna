package com.models;

public class Transfer {
	private int id;
	private Account accountOne;
	private Account accountTwo;
	private double amount;
	
	Transfer (int id, Account accountOne, Account accountTwo, double amount){
		this.id = id;
		this.accountOne = accountOne;
		this.accountTwo = accountTwo;
		this.amount = amount;
	}
	
	Transfer (Account accountOne, Account accountTwo, double amount){
		this.accountOne = accountOne;
		this.accountTwo = accountTwo;
		this.amount = amount;
	}
	
	Transfer() {
		super();
	}

	public Account getAccountOne() {
		return accountOne;
	}

	public void setAccountOne(Account accountOne) {
		this.accountOne = accountOne;
	}

	public Account getAccountTwo() {
		return accountTwo;
	}

	public void setAccountTwo(Account accountTwo) {
		this.accountTwo = accountTwo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}
	
	

}
