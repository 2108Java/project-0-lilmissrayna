package com.models;

public class UserType {
	private int id;
	private String name;
	
	UserType(){
		this.name = "";
	}
	
	UserType(String name){
		this.name = name;
	}

	UserType(int id, String name){
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
