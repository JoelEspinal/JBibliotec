package com.joel.models;

public enum Type {

	BOOK("Book"), PROYECT("Proyect"), VIEWRES("Viewer");
	
	private String name;
	
	Type(String name){
		this.name= name;
	}
	@Override
	public String toString() {
		return name;
	}
	public static Type getValueOf(String str){
		for(Type type : Type.values()){
			if(str.equals(type.toString())) return type;
		}
		return null;
	}
}
