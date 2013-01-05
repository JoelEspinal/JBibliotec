package com.joel.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Box.Filler;

import com.joel.models.BibliograficMaterial;
import com.joel.models.Client;
import com.joel.models.Loan;
import com.joel.models.LoanDetail;
import com.joel.tools.LoanHelper;
import com.joel.utils.Util;
import com.joel.views.ManageLoan;

public class ManageLoanController {

	private static ManageLoan manage;
	private static int idLoan;
	private static LoanHelper loanHelp;
	
	public static void init(JFrame front){
		manage = new ManageLoan();
		front.setContentPane(manage);
		getSelectMaterialAction();
		getLoanHelperOkAction();
		getSaveLoanAction();
		removeMaterial();
		setClearAction();
		editAction();
		setRemoveLoanAction();
		front.setVisible(true);
	}
	public static void clearAction(){
		idLoan= 0;
		manage.getLoanDetails().clear();
		manage.setCurrentLoan(null);
		manage.fillLoanDetail();
	}
	public static void setClearAction(){
		manage.getClear().addActionListener(new	ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAction();
			}
		});
	}
	public static LoanHelper getLoanHelper(){
		if(loanHelp == null){
			loanHelp= new LoanHelper();
		}
		return loanHelp;
	}
	public static void getSelectMaterialAction(){
		manage.getSelectBook().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getLoanHelper().setVisible(true);
			}
		});
	}
	public static void getLoanHelperOkAction(){
		getLoanHelper().getOK().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getLoanHelper().getMaterial().getSelectedIndex() != -1 && getLoanHelper().getLoanDay().getSelectedIndex() != -1){
					BibliograficMaterial material= ((BibliograficMaterial)getLoanHelper().getMaterial().getSelectedItem());
					Date dateLine= Util.addDays(new Date(),((int)getLoanHelper().getLoanDay().getSelectedItem()));
					LoanDetail detail= new LoanDetail(0.0, 0, material.getId(), dateLine);
					System.out.println("Decicocccccccccccccc");
					if(!manage.getLoanDetails().contains(detail)){
						manage.getLoanDetails().add(detail);
						System.out.println("AAAAAAAAAAAagggggggrrrrrrrreeeeeeggggggggaaaddddddddddddooooooooo");
					}
					manage.fillLoanDetail();
					getLoanHelper().dispose();
				}
			}
		});
	}
	public static void removeMaterial(){
		manage.getRemoveBook().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(manage.getLoanDetailDefaultModel().getRowCount() != 0){
					if(manage.getLoanDetailTable().getSelectedRowCount() > 0){
						removeMaterialAction();
						clearAction();
					}
				}
			}
		});
	}
	public static void getSaveLoanAction(){
		manage.getSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(manage.getClient().getSelectedIndex() != -1 && !manage.getLoanDetails().isEmpty()){
					System.out.println("Entrando al metodo save");
					Loan loan= new Loan();
					loan.setLoanDate(new Date());
					loan.setClientId(((Client)manage.getClient().getSelectedItem()).getId());
					loan.setEmployeeId(MainController.loginEmployee.getId());
					if(idLoan > 0){loan.setId(idLoan);}
					System.out.println("guardando el prestamo");
					loan.save();
					System.out.println("Prestamo guardado");
					List<LoanDetail> loans= loan.getLoanDetails();
					for(LoanDetail loanDetail : loans){
						loan.removeLoanDetail(loanDetail);
						System.err.println("Removing: "+loanDetail.toString());
					}
					for(LoanDetail ld : manage.getLoanDetails()){
						ld.setLoadId(loan.getId());
						ld.save();
					}
					manage.fillLoans();
					System.out.println("Limpinando el JTable");
					clearAction();
				}
			}
		});
	}
	public static void removeMaterialAction(){
		int row= manage.getTable().getSelectedRowCount();
		System.out.println("Row: "+ row);
		System.out.println(">>>>>>>>>>>>" + manage.getLoanDetailDefaultModel().getValueAt(row, 1));
		int materialId = (BibliograficMaterial.findByTitle((String)manage.getLoanDetailDefaultModel().getValueAt(row,1))).getId();
		for(Iterator<LoanDetail> detailList=  manage.getLoanDetails().iterator();detailList.hasNext();){
			final LoanDetail detail=detailList.next();
			if(detail.getBibliograficMaterialId() == materialId){
				detailList.remove();
			}
		}
		manage.fillLoanDetail();
	}
	public static void setRemoveLoanAction(){
		manage.getDelete().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				idLoan = Integer.parseInt(manage.getTableModel().getValueAt(manage.getTable().getSelectedRow(), 0).toString());
				Loan loan= Loan.find(idLoan);
				for(Iterator<LoanDetail> detailList= loan.getLoanDetails().iterator(); detailList.hasNext();){
					final LoanDetail detail= detailList.next();
					detail.destroy();
				}
				loan.destroy();
				setClearAction();
				manage.fillLoans();
			}
		});
	}
	public static void editAction(){
		manage.getEdit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				idLoan = Integer.parseInt(manage.getTableModel().getValueAt(manage.getTable().getSelectedRow(), 0).toString());
				manage.setCurrentLoan(Loan.find(idLoan));
				manage.fillLoanDetail();
			}
		});
	}
}
