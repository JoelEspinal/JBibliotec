package com.joel.models;

public enum Role {

	EMPLOYEE("Employee"), CLIENT("Client");
	
	private String name;
	
	Role(String name){
		this.name= name;
	}
	@Override
	public String toString() {
		return name;
	}
}
