package com.joel.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.joel.schema.Migrate;

public class SQLiteConnection extends AbstractConnection{

	private static Connection connection;
	private static SQLiteConnection instance;
	
	private SQLiteConnection(){

	}
	public static SQLiteConnection getConnection(){
		if(instance == null){
			instance= new SQLiteConnection();
			instance.createTables();
		}
		return instance;
	}
	public Connection startConnection()throws Exception{
		close();
		Class.forName("org.sqlite.JDBC");
		connection= DriverManager.getConnection("jdbc:sqlite:jbibliotec.db");
		return connection;
	}
	private void createTables(){
		Migrate migrate= new Migrate();
		SQLiteConnection connection= null;
		try{
			connection= SQLiteConnection.getConnection();
		}catch(Exception e){e.printStackTrace();}
		for(String sql : migrate.values()){
			try{
				connection.executeUpdate(sql);
			}catch(Exception e){    }
		}
	}
	@Override
	public ResultSet executeQuery(String sql) throws Exception {
		System.out.println(sql);
		ResultSet result= startConnection().createStatement().executeQuery(sql);
		return result;
	}

	@Override
	public boolean executeUpdate(String sql) throws Exception {
		System.out.println(sql);
		startConnection().createStatement().executeUpdate(sql);
		close();
		return true;
	}
	public static void close() {
		try{
			if(connection != null){connection.close();}
		}catch(Exception e){e.printStackTrace();}
		
	}
}
