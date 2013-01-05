package com.joel.views;

import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TemplateView extends Panel{

	protected JLabel label;
	protected JButton save;
	protected JButton edit;
	protected JButton delete;
	protected JScrollPane scroll;
	protected JButton clear;
	protected JTable table;
	protected DefaultTableModel defaultModel;
	
	
	public JLabel getLabel(String text, Rectangle rect){
		label = new JLabel(text);
		label.setBounds(rect);
		return label;
	}
	public JButton getSave(){
		if(save == null){
			save= new JButton("Save");
		}
		return save;
	}
	public JButton getEdit(){
		if(edit == null){
			edit= new JButton("Edit");
		}
		return edit;
	}
	public JButton getClear(){
		if(clear == null){
			clear= new JButton("Clear");
		}
		return clear;
	}
	public JButton getDelete(){
		if(delete == null){
			delete= new JButton("Delete");
		}
		return delete;
	}
	public JScrollPane getScroll(){
		if(scroll == null){
			scroll= new JScrollPane(getTable());
		}
		return scroll;
	}
	public JTable getTable(){
		if(table == null){
			table= new JTable(getTableModel());
		}
		return table;
	}
	public DefaultTableModel getTableModel(){
		if(defaultModel == null){
 			defaultModel= new DefaultTableModel(){
 				@Override
 				public boolean isCellEditable(int row, int column) {
 					return false;
 				}
 			};
		}
		return defaultModel;
	}
}
