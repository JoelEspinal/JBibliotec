package com.joel.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Login extends JFrame{

	private JPanel superPanel;
	private JTextField user;
	private JPasswordField password;
	private JButton login;
	private JButton cancel;
	
	 public Login(){
        super();
        setSize(315, 180);
        setContentPane(getSuperPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
	 public JPanel getSuperPanel(){
		 if(superPanel == null){
			 superPanel = new JPanel(true);
			 superPanel.setLayout(null);
			 superPanel.add(getUser());
			 superPanel.add(getPassword());
			 superPanel.add(getLogin());
			 superPanel.add(getCancel());
			 
			 JLabel lblUserName = new JLabel("User Name:");
			 lblUserName.setBounds(20, 24, 75, 20);
			 superPanel.add(lblUserName);
			 
			 JLabel lblPassword = new JLabel("Password:");
			 lblPassword.setBounds(20, 69, 75, 20);
			 superPanel.add(lblPassword);
		 }
		 return superPanel;
	 }
	 public JTextField getUser(){
		 if(user == null){
			 user = new JTextField();
			 user.setLocation(95, 20);
			 user.setSize(177, 28);
		 }
		 return user;
	 }
	 public JPasswordField getPassword(){
		 if(password == null){
			 password = new JPasswordField();
			 password.setLocation(95, 60);
			 password.setSize(177, 28);
		 }
		 return password;
	 }
	 public JButton getLogin(){
		 if(login == null){
			 login = new JButton("Login");
			 login.setSize(93, 32);
			 login.setLocation(74, 103);
		 }
		 return login;
	 }
	 public JButton getCancel(){
		 if(cancel == null){
			 cancel = new JButton("Cancel");
			 cancel.setSize(93, 32);
			 cancel.setLocation(179, 103);
		 }
		 return cancel;
	 }
}
