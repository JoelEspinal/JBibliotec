package com.joel.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.joel.models.BibliograficMaterial;

public class LoanHelper extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<BibliograficMaterial> material;
	private List<Integer> numbers;
	private JComboBox<Integer> day;
	private JButton ok;
	private JButton cancel;
	
	public LoanHelper(){
		setSize(450, 249);
		setLayout(null);
		add(getMaterial());
		add(getLoanDay());
		add(getOK());
		add(getCancel());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(false);
	}

	public JComboBox<BibliograficMaterial> getMaterial() {
		if(material == null){
			material= new JComboBox<>(BibliograficMaterial.getAll().toArray(new BibliograficMaterial[]{}));
			material.setBounds(20, 50, 225, 33);
			material.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					BibliograficMaterial ma= ((BibliograficMaterial)material.getSelectedItem());
					int i=1;
						if(ma.getType()==com.joel.models.Type.PROYECT || ma.getType()== com.joel.models.Type.VIEWRES){ 
							getNumbers().clear();
							getLoanDay().removeAllItems();
								for(;i <= 15; i++){
									getNumbers().add(new Integer(i));
								}
								for(Integer inte :getNumbers()){

									getLoanDay().addItem(inte);
								}
						}
						else if(ma.getType()==com.joel.models.Type.BOOK){
							getNumbers().clear();
							getLoanDay().removeAllItems();
							for(;i <= 7; i++){
								getNumbers().add(new Integer(i));
							}
							for(Integer inte :getNumbers()){

								getLoanDay().addItem(inte);
							}
						}
				}
			});
			repaint();
			revalidate();
		}
		return material;
	}

	public JComboBox<Integer> getLoanDay(){
		if(day == null){
			day= new JComboBox<Integer>(getNumbers().toArray(new Integer[]{}));
			day.setBounds(280, 50, 75, 33);
		}
		return day;
	}
	public List<Integer> getNumbers(){
		if(numbers == null){
			numbers= new ArrayList<Integer>();
		}
		return numbers;
	}
	public JButton getOK(){
		if(ok == null){
			ok= new JButton("OK");
			ok.setBounds(80, 130, 60, 35);
		}
		return ok;
	}
	public JButton getCancel(){
		if(cancel == null){
			cancel= new JButton("Cancel");
			cancel.setBounds(280, 130, 70, 35);
			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return cancel;
	}
}