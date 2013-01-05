package com.joel.schema;

import java.util.HashMap;

@SuppressWarnings("serial")
public class Migrate extends HashMap<String, String>{

	public Migrate(){
		put("persons", "create table persons(" +
				"id integer primary key autoincrement,"+
				"name varchar(50) not null," +
				"last_name varchar(50) not null," +
				"id_card varchar(14)," +
				"dob date not null," +
				"gender varchar(1) not null," +
				"nationality varchar(50) not null," +
				"phone_number varchar(14)," +
				"mobile_number varchar(14)," +
				"city varchar(50) not null," +
				"main_address varchar(255) not null," +
				"alternative_address varchar(255)," +
				"zip_code varchar(4) not null"+
				")"
		);
		put("clients", "create table clients(" +
				"id integer primary key autoincrement," +
				"item_limit integer," +
				"status varchar(45) not null," +
				"person_id integer references persons(id)" +
				")");
		put("employees", "create table employees(" +
				"id integer primary key autoincrement," +
				"person_id integer references persons(id)," +
				"user_name varchar(50) not null," +
				"password varchar(50) not null" +
				")");
		put("bibliografic_materials", "create table bibliografic_material(" +
				"id integer primary key autoincrement," +
				"title varchar(50) not null," +
				"publication_date date not null," +
				"penalty decimal(4,2)," +
				"type varchar(45) not null" +
				")");
		put("authors", "create table authors(" +
				"id integer primary key autoincrement," +
				"name varchar(45) not null," +
				"last_name varchar(45) not null," +
				"bibliografic_material_id integer references bibliografic_material(id)" +
				")");
		put("author_bibliografic_material", "create table author_bibliografic_material(" +
				"author_id integer references authors(id)," +
				"bibliografic_material_id integer references bibliografic_material(id)," +
				"primary key(author_id, bibliografic_material_id)" +
				")");
		put("loans", "create table loans(" +
				"id integer primary key autoincrement," +
				"loan_date date not null," +
				"employee_id integer references employees(id)," +
				"client_id integer references clients(id)"+
				")");
		put("loan_details", "create table loan_details(" +
				"id integer primary key autoincrement," +
				"deadline date," +
				"loan_id integer references loans(id),"  +
				"bibliografic_material_id integer references bibliografic_material(id)" +
				")");
	}
}
