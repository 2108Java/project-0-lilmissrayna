package com.models;

public class AccountType {
	private int id;
	private String name;
	
	public AccountType(){
		this.name = "";
	}
	
	AccountType(String name){
		this.name = name;
	}
	
	public AccountType(int id, String name){
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

}
