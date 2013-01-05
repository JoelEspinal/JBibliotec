package com.joel.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.joel.models.Client;
import com.joel.models.Employee;
import com.joel.models.Gender;
import com.joel.models.Person;
import com.joel.models.Role;
import com.joel.views.ManagePeople;

public class ManagePeopleController {
	
	private static int id;
	private static ManagePeople manage;
	
	public static void init(JFrame front){
		manage= new ManagePeople();
		front.setContentPane(manage);
		setClearAction();
		setRollSelectionAction();
		setTableRollSelectionAction();
		setSaveAction();
		setEditAction();
		setDeleteAction();
		front.setVisible(true);
	}
	
	private static void setClearAction(){
		manage.getClear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearAction();
			}
		});
	}
	private static void clearAction(){
		id=0;
		manage.getPersonName().setText("");
		manage.getUserName().setText("");
		manage.getLastName().setText("");
		manage.getIdCard().setText("");
		manage.getDob().setDate(new Date());
		manage.getNationality().setText("");
		manage.getPhoneNumber().setText("");
		manage.getMobileNumber().setText("");
		manage.getMainAddress().setText("");
		manage.getAlternativeAddress().setText("");
		manage.getZipCode().setText("");
		manage.getPassword().setText("");
		manage.getCity().setText("");
		manage.getRollSelection().setEnabled(true);
	}
	private static void setSaveAction(){
		manage.getSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!manage.getPersonName().getText().isEmpty() || !manage.getLastName().getText().isEmpty() || !manage.getNationality().getText().isEmpty() || !manage.getMainAddress().getText().isEmpty() || !manage.getZipCode().getText().isEmpty()){
					saveAction();
					selectItemAction();
					manage.fill((Role)manage.getRollSelection().getSelectedItem());
					setClearAction();
				}
				else{
					JOptionPane.showMessageDialog(null,"Fields with \"*\" are required", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
	}
	@SuppressWarnings("deprecation")
	private static void saveAction(){
		Person person= new Person(manage.getPersonName() .getText(), manage.getLastName().getText(),manage.getDob().getDate(), (Gender)manage.getGender().getSelectedItem(), manage.getNationality().getText(), manage.getCity().getText(), manage.getMainAddress().getText(), manage.getZipCode().getText());
		if(id > 0){
			if(manage.getRollSelection().getSelectedItem().equals(Role.CLIENT)){
				person.setId(Client.find(id).getPersonId());
			}
			else{
				person.setId(Employee.find(id).getPersonId());
			}
		}
		person.setIdCard(manage.getIdCard().getText());
		person.setPhoneNumber(manage.getPhoneNumber().getText());
		person.setMobileNumber(manage.getMobileNumber().getText());
		person.setAlternativeaAddress(manage.getAlternativeAddress().getText());
	 	if(manage.getRollSelection().getSelectedItem().equals(Role.CLIENT)){
		 		person.save();
		 		Client client= new Client(person.getId(), "Client");
		 		if(id > 0){client.setId(id);}
		 		client.save();
		 		clearAction();
		 		manage.fill(Role.CLIENT);
		}
	 	else if(manage.getRollSelection().getSelectedItem().equals( Role.EMPLOYEE)){
		 	if(!manage.getPassword().getText().isEmpty() || !manage.getUserName().getText().isEmpty()){	
		 		person.save();
		 		Employee employee= new Employee(person.getId(), manage.getUserName().getText(), manage.getPassword().getText());
		 		if(id > 0){employee.setId(id);}
		 		employee.save();
		 		clearAction();
		 		manage.fill(Role.EMPLOYEE);
		 	}
		 	else{
		 		JOptionPane.showMessageDialog(null,"User name or password are required", "Warning", JOptionPane.WARNING_MESSAGE);
		 	}
	 	}
	}
	private static void setRollSelectionAction(){
		manage.getRollSelection().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rollAction();
			}
		});
	}
	private static void setTableRollSelectionAction(){
		manage.getItemList().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				selectItemAction();
			}
		});
		manage.getItemList().setSelectedItem(Role.CLIENT);
	}
	private static void rollAction(){
		if(manage.getRollSelection().getSelectedItem() == Role.CLIENT){
			manage.getUserName().setEnabled(false);
			manage.getPassword().setEnabled(false);
		}
		else if(manage.getRollSelection().getSelectedItem() == Role.EMPLOYEE){
			manage.getUserName().setEnabled(true);
			manage.getPassword().setEnabled(true);
		}
	}
	private static void setEditAction(){
		manage.getEdit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(manage.getTable().getSelectedRow()>=0)
					editAction();
				else
					JOptionPane.showMessageDialog(null, "You Must to Select a "+manage.getItemList().getSelectedItem()+" to Edit it.", "Warning", JOptionPane.INFORMATION_MESSAGE);
				selectItemAction();
			}
		});	
	}
	private static void editAction(){
		Role role = (Role) manage.getItemList().getSelectedItem();
		Person person = null;
		manage.getRollSelection().setEnabled(false);
		if(role.equals(Role.EMPLOYEE)){
			id= Integer.parseInt(manage.getTableModel().getValueAt(manage.getTable().getSelectedRow(), 0).toString());
			Employee employee= Employee.find(id);
			manage.getUserName().setText(employee.getUserName());
			manage.getPassword().setText(employee.getPassword());
			person = employee.getPerson();
		}
		if(role.equals(Role.CLIENT)){
			id= Integer.parseInt(manage.getTableModel().getValueAt(manage.getTable().getSelectedRow(), 0).toString());
			Client client= Client.find(id);
			person = client.getPerson();
			manage.getUserName().setEnabled(false);
			manage.getPassword().setEnabled(false);
		}
		manage.getPersonName().setText(person.getName());
		manage.getLastName().setText(person.getLastName());
		manage.getIdCard().setText(person.getIdCard());
		manage.getDob().setDate(person.getDob());
		manage.getGender().setSelectedItem(person.getGender());
		manage.getNationality().setText(person.getNationality());
		manage.getPhoneNumber().setText(person.getPhoneNumber());
		manage.getMobileNumber().setText(person.getMobileNumber());
		manage.getMainAddress().setText(person.getMainAddress());
		manage.getRollSelection().setSelectedItem(role);
		manage.getZipCode().setText(person.getZipCode());
		manage.getAlternativeAddress().setText(person.getAlternativeAddress());
		manage.getCity().setText(person.getCity());
	}
	private static void setDeleteAction(){
		manage.getDelete().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(manage.getTable().getSelectedRow()>=0){
					if(JOptionPane.showConfirmDialog(null, "Do you Really Want to Remove the "+manage.getItemList().getSelectedItem(), "Confirmation", JOptionPane.YES_NO_OPTION)==0)
						deleteAction();
				}
				else
					JOptionPane.showMessageDialog(null, "You Must to Select a "+manage.getItemList().getSelectedItem()+" to Delete it.", "Warning", JOptionPane.INFORMATION_MESSAGE);
				selectItemAction();
			}
		});	
	}
	private static void deleteAction(){
		int id= Integer.parseInt(manage.getTableModel().getValueAt(manage.getTable().getSelectedRow(), 0).toString());
		Role role = (Role) manage.getItemList().getSelectedItem();
		if(role.equals(Role.EMPLOYEE)){ Employee.find(id).destroy();}
		
		else Client.find(id).destroy();
	}
	private static void selectItemAction(){
		manage.fill((Role)manage.getItemList().getSelectedItem());
	}
}
