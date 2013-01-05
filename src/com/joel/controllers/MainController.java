package com.joel.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.joel.connections.AbstractConnection;
import com.joel.connections.MYSQLConnection;
import com.joel.connections.SQLiteConnection;
import com.joel.models.Employee;
import com.joel.views.Front;
import com.joel.views.Login;

public class MainController {

	public static Login login;
	public static Front front;
	public static Employee loginEmployee;
	
	public static void init(){
		AbstractConnection.connection = SQLiteConnection.getConnection();
		login= new Login();
		login();
		cancel();
	}
	
	public static void login(){
		login.getLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginAction();
			}
		});
	}
	public static void cancel(){
		login.getCancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	public static void loginAction(){
		int id= Employee.validate(login.getUser().getText(), login.getPassword().getText()); 
		if( id != 0){
			loginEmployee= Employee.find(id);
			login.dispose();
			front= new Front();
			front.setVisible(true);
		}
		else{
			JOptionPane.showMessageDialog(null, "The user name or password are incorrect", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void logout(){
		front.dispose();
		loginEmployee= null;
		if(AbstractConnection.getConnection() instanceof SQLiteConnection){
			((SQLiteConnection)AbstractConnection.getConnection()).close();
		}
		else if(AbstractConnection.getConnection() instanceof MYSQLConnection){
			((MYSQLConnection) AbstractConnection.getConnection()).close();
			
		}
		login.getUser().setText("");
		login.getPassword().setText("");
		login.setVisible(true);
	}
}
