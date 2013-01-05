package com.joel.models;

public enum Gender {

	MALE("Male"),FEMALE("Female");
	
	private String gender;
	
	private Gender(String gender){
		this.gender= gender;
	}
	
	@Override
	public String toString() {
		return gender;
	}
	
	public static Gender getValueOf(String str){
		for(Gender gender : Gender.values()){
			if(str.equals(gender.toString())) return gender;
		}
		return null;
	}
	
}
