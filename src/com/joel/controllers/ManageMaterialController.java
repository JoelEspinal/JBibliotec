package com.joel.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.joel.models.Author;
import com.joel.models.BibliograficMaterial;
import com.joel.models.Type;
import com.joel.views.ManageMaterial;

public class ManageMaterialController {

	private static ManageMaterial manage;
	private static int idMaterial;
	
	public static void init(JFrame front){
		manage = new ManageMaterial();
		front.setContentPane(manage);
		front.repaint();
		front.revalidate();
		setSaveAction();
		setEditAction();
		setClearAction();
		getCreateAuthorAction();
		getSelectAutorAction();
		setDeleteAuthorAction();
		setRemoveMaterial();
		front.setVisible(true);
	}
	public static void setClearAction(){
		manage.getClear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearAction();
			}
		});
	}
	public static void clearAction(){
		idMaterial= 0;
		manage.getTitle().setText("");
		manage.getPublicationDate().setDate(new Date());
		manage.getType().setSelectedItem(Type.BOOK);
		manage.getDefaultAuthor().clear();
		manage.getAuthors().clear();
	}
	public static void setSaveAction(){
		manage.getSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveAction();
			}
		});
	}
	public static void saveAction(){
		// falta poner un JOptionPane que diga que es requerido el titulo
		if(!manage.getTitle().getText().isEmpty()){
			BibliograficMaterial book= new BibliograficMaterial();
			book.setTitle(manage.getTitle().getText());
			book.setPublicationDate(manage.getPublicationDate().getDate());
			book.setType((Type)manage.getType().getSelectedItem());
			if(idMaterial > 0){book.setId(idMaterial);}
			book.save();
			List<Author> authors =book.getAuthors();
			for(Author author : authors){
				book.removeAuthor(author);
				System.err.println("Removing: "+author.toString());
			}
			for (int i = 0; i < manage.getDefaultAuthor().getSize(); i++) {
				book.addAuthor(manage.getDefaultAuthor().getElementAt(i));
				System.err.println("Adding: "+manage.getDefaultAuthor().getElementAt(i));
			}
			clearAction();
			manage.fillMaterial();
		}
	}
	public static void setEditAction(){
		manage.getEdit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(manage.getTable().getSelectedRow()>=0){
					editAction();
				}
			}
		});
	}
	public static void editAction(){
		idMaterial= Integer.parseInt(manage.getTableModel().getValueAt(manage.getTable().getSelectedRow(), 0).toString());
		BibliograficMaterial material= BibliograficMaterial.find(idMaterial);
		manage.getTitle().setText(material.getTitle());
		manage.getPublicationDate().setDate(material.getPublicationDate());
		manage.getType().setSelectedItem(material.getType());
		manage.setCurrrentMaterial(material);
		manage.authorFill();
	}
	public static void setDeleteAction(){
		manage.getDelete().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteAction();
			}
		});
	}
	public static void deleteAction(){
		idMaterial= Integer.parseInt(manage.getTableModel().getValueAt(manage.getTable().getSelectedRow(), 0).toString());
		BibliograficMaterial.find(idMaterial).destroy();
	}
	public static void getSelectAutorAction(){
		manage.getSelect().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Author author= (Author)JOptionPane.showInputDialog(null,"Select a Author: ","Author Selection",JOptionPane.PLAIN_MESSAGE,null,Author.getAll().toArray(new Author[]{}),null);
				if(author != null){
					manage.getAuthors().add(author);
					manage.authorFill();
				}
			}
		});
	}
	public static void getCreateAuthorAction(){
		manage.getAddAuthor().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 Object[] inputs = {new JLabel("Name: "), manage.getAuthorName(), new JLabel("Last Name: "), manage.getAuthorLastName()};
				  boolean create = JOptionPane.showConfirmDialog(null,inputs,"Author Creation",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)==0;
				  if(create){
					  if(!manage.getAuthorName().getText().isEmpty() || !manage.getAuthorLastName().getText().isEmpty()){
						  Author author= new Author();
						  author.setName(manage.getAuthorName().getText());
						  author.setLastName(manage.getAuthorLastName().getText());
						  author.save();
						  manage.getAuthors().add(author);
						  manage.authorFill();  
					  }
				  }
			}
		});
	}
	public static void setDeleteAuthorAction(){
		manage.getRemoveAuthor().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(manage.getAuthorList().getSelectedIndex() >= 0){
					Author author= manage.getDefaultAuthor().getElementAt(manage.getAuthorList().getSelectedIndex());
					manage.getAuthors().remove(author);
					manage.authorFill();
				}
			}
		});
	}
	public static void setRemoveMaterial(){
		manage.getDelete().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				idMaterial= Integer.parseInt(manage.getTableModel().getValueAt(manage.getTable().getSelectedRow(), 0).toString());
				System.out.println(idMaterial);
				if(idMaterial != 0){
					BibliograficMaterial material= BibliograficMaterial.find(idMaterial);
					for(Author author : material.getAuthors()){
						material.removeAuthor(author);
					}
					material.destroy();
					idMaterial= 0;
					manage.fillMaterial();
				}
			}
		});
	}
}
