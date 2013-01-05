package com.joel.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class MYSQLConnection extends AbstractConnection{

	private static Connection connection;
	private static MYSQLConnection instance;
	
	
	private MYSQLConnection(){
		
	}
	public static MYSQLConnection getConnection(){
		if(instance == null){
			instance= new MYSQLConnection();
		}
		return instance;
	}
	public static Connection startConnection() throws Exception{
			close();
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost/jbibliotec.db", "root", "1234");
		return connection;
	}
	@Override
	public ResultSet executeQuery(String sql) throws Exception{
		System.out.println(sql);
		ResultSet result = startConnection().createStatement().executeQuery(sql);
		return result;
	}

	@Override
	public boolean executeUpdate(String sql)throws Exception{
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
