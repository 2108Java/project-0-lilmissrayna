package com.models;

public class Transfer {
	private int id;
	private int accountOne;
	private int accountTwo;
	private double amount;
	
	public Transfer (int id, int accountOne, int accountTwo, double amount){
		this.id = id;
		this.accountOne = accountOne;
		this.accountTwo = accountTwo;
		this.amount = amount;
	}
	
	Transfer() {
		super();
	}

	public int getAccountOne() {
		return accountOne;
	}

	public void setAccountOne(int accountOne) {
		this.accountOne = accountOne;
	}

	public int getAccountTwo() {
		return accountTwo;
	}

	public void setAccountTwo(int accountTwo) {
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
