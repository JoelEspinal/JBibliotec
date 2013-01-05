package com.joel.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.joel.controllers.MainController;
import com.joel.controllers.ManageLoanController;
import com.joel.controllers.ManageMaterialController;
import com.joel.controllers.ManagePeopleController;

@SuppressWarnings("serial")
public class Front extends JFrame{

	private JMenuBar menu;
	private JMenu file;
	private JMenu manage;
	
	private JMenuItem logout;
	private JMenuItem exit;

	private JMenuItem material;
	private JMenuItem people;
	private JMenuItem loan;
	
	public Front(){
		getContentPane().setLayout(new BorderLayout());
		setJMenuBar(getMenu());
		setSize(778, 560);
		setMinimumSize(new Dimension(777, 529));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public JMenuBar getMenu(){
		if(menu == null){
			menu= new JMenuBar();
			menu.add(getFile());
			menu.add(getManage());
		}
		return menu;
	}
	public JMenu getFile(){
		if(file == null){
			file= new JMenu("JBibliotec");
			file.setLayout(new GridLayout(2, 1));
			file.add(getLogout());
			file.add(getExit());
		}
		return file;
	}
	public JMenuItem getLogout(){
		if(logout == null){
			logout= new JMenuItem("LogOut");
			logout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(JOptionPane.showConfirmDialog(null, "Sure wish log out", "Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0){
						MainController.logout();
					}
				}
			});
		}
		return logout;
	}
	public JMenuItem getExit(){
		if(exit == null){
			exit= new JMenuItem("Exit");
			exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null, "Do you want to exit?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0){
						System.exit(EXIT_ON_CLOSE);
					}
				}
			});
		}
		return exit;
	}
	public JMenu getManage(){
		if(manage == null){
			manage= new JMenu("Manage");
			manage.add(getMaterial());
			manage.add(getPeople());
			manage.add(getLoan());
		}
		return manage;
	}
	public JMenuItem getMaterial(){
		if(material == null){
			material= new JMenuItem("Bibliografic Material");
			material.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ManageMaterialController.init(Front.this);
				}
			});
		}
		return material;
	}
	public JMenuItem getPeople(){
		if(people == null){
			people= new JMenuItem("People");
			people.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ManagePeopleController.init(Front.this);
				}
			});
		}
		return people;
	}
	public JMenuItem getLoan(){
		if(loan == null){
			loan= new JMenuItem("Loan");
			loan.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ManageLoanController.init(Front.this);
				}
			});
		}
		return loan;
	}
}
