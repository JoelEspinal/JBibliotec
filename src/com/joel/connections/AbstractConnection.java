package com.joel.connections;

import java.sql.ResultSet;

public abstract class AbstractConnection{
	
	public static AbstractConnection connection;
	
	public abstract ResultSet executeQuery(String sql)throws Exception;
	public abstract boolean executeUpdate(String sql)throws Exception;
	public static AbstractConnection getConnection(){
		return connection;
	}
}
