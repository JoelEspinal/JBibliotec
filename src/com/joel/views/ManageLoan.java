package com.joel.views;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.joel.models.BibliograficMaterial;
import com.joel.models.Client;
import com.joel.models.Employee;
import com.joel.models.Loan;
import com.joel.models.LoanDetail;

@SuppressWarnings("serial")
public class ManageLoan extends TemplateView{

	private JComboBox<Client> client;
	private JScrollPane loanDetailScroll;
	private JTable loanDetailTable;
	private DefaultTableModel loanDetailDefaulttModel;
	private JScrollPane  scrolTablelPanel;
	private JPanel SaveClear;
	private JPanel editDelete;
	private List<Client> clients;
	private JPanel panel;
	private JButton selectBook;
	private JButton removeBook;
	private HashSet<LoanDetail> loanDetails;
	private Loan currentLoan;
	private int itemLimit;
	
	public ManageLoan(){
		clients= Client.getAll();
		setLayout(null);
		add(getClient());
		add(getLoanDetailScroll());
		add(getSaveClear());
		add(getEditDelete());
		add(getTablePanel());
		add(getBookControlPanel());
		add(getLabel("Client", 		new Rectangle(505, 70, 46, 14)));
		add(getLabel("Loan Detail: ", new Rectangle(40, 20, 70, 20)));
		fillLoans();
		fillLoanDetail();
	}
	public JScrollPane getLoanDetailScroll(){
		if(loanDetailScroll == null){
			loanDetailScroll= new JScrollPane(getLoanDetailTable());
			loanDetailScroll.setBounds(42, 34, 323, 136);
		}
		return loanDetailScroll;
	}
	public JPanel getBookControlPanel(){
		if(panel==null){
			panel= new JPanel(new FlowLayout());
			
			panel.setBounds(100, 177, 235, 45);
			panel.add(getSelectBook());
			panel.add(getRemoveBook());
		}
		return panel;
	}
	public JButton getSelectBook(){
		if(selectBook == null){
			selectBook= new JButton("Add material");
		}
		return selectBook;
	}
	public JButton getRemoveBook(){
		if(removeBook == null){
			removeBook= new JButton("remove");
		}
		return removeBook;
	}
	public JComboBox<Client> getClient() {
		if(client == null){
			client= new JComboBox<Client>();
			for(Client cli : Client.getAll()){
				client.addItem(cli);
			}
			client.setBounds(505, 89, 136, 33);
		}
		return client;
	}
	public DefaultTableModel getLoanDetailDefaultModel() {
		if(loanDetailDefaulttModel == null){
			loanDetailDefaulttModel= new DefaultTableModel(){
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
		}
		return loanDetailDefaulttModel;
	}
	public JTable getLoanDetailTable() {
		if(loanDetailTable == null){
			loanDetailTable= new JTable(getLoanDetailDefaultModel());
			loanDetailTable.setBounds(211, 15, 0, 0);
		}
		return loanDetailTable;
	}
	public JScrollPane getTablePanel(){
		if(scrolTablelPanel == null){
			scrolTablelPanel= new JScrollPane(getTable());
			scrolTablelPanel.setBounds(30, 221, 649, 185);
		}
		return scrolTablelPanel;
	}
	public JPanel getSaveClear() {
		if(SaveClear == null){
			SaveClear= new JPanel();
			SaveClear.setBounds(505, 152, 136, 33);
			SaveClear.add(getSave());
			SaveClear.add(getClear());
		}
		return SaveClear;
	}
	public JPanel getEditDelete() {
		if(editDelete == null){
			editDelete= new JPanel();
			editDelete.setBounds(463, 417, 190, 48);
			editDelete.add(getEdit());
			editDelete.add(getDelete());
		}
		return editDelete;
	}
	public void fillLoans(){
		getTableModel().setRowCount(0);
		getTableModel().setColumnIdentifiers(new String []{"Loan ID", "Client ID", "Loan Date", "Employee"});
		for(Loan loan : Loan.getAll()){
			String date= new SimpleDateFormat("MM/dd/yyyy").format(loan.getLoanDate());
			getTableModel().addRow(new String[]{loan.getId()+"", loan.getClientId()+"", date, Employee.find(loan.getEmployeeId()).getUserName()});	
		}
	}
	public void fillLoanDetail(){
		
		/*
		 * public void fillMaterial(){
		getTableModel().setRowCount(0);
		getTableModel().setColumnIdentifiers(new String []{"Id", "Title", "Type", "Publication Date"});
		for(BibliograficMaterial material : BibliograficMaterial.getAll()){
			getTableModel().addRow(new String[]{material.getId()+ "", material.getTitle(), material.getType().toString(), new SimpleDateFormat("MM/dd/yyyy").format(material.getPublicationDate())});
		}
	}
		 * 
		 */
		
		
		
		getLoanDetailDefaultModel().setRowCount(0);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + getLoanDetails().size());
		getLoanDetailDefaultModel().setColumnIdentifiers(new String []{"detail Id", "Material", "Dead Line", "Ticket"});
		if(getCurrentLoan() != null){
			for(LoanDetail detail : getCurrentLoan().getLoanDetails()){
				String deadLine= new SimpleDateFormat("MM/dd/yyyy").format(detail.getDeadline());
				getLoanDetailDefaultModel().addRow(new String[]{detail.getId()+"", BibliograficMaterial.find(detail.getBibliograficMaterialId()).getTitle(), 
						deadLine, detail.getTicket()+""});
			}
		}
		else{
			System.out.println("Eeeeeeeeeeeeeeeeeeeeeeeeeelllllllllllllllllssssssssssseeeeeeeeeeeeeeeeeeee");
			for(LoanDetail detail : this.getLoanDetails()){
				System.out.println(detail.getDeadline());
				String deadLine= new SimpleDateFormat("MM/dd/yyyy").format(detail.getDeadline());
				getLoanDetailDefaultModel().addRow(new String[]{detail.getId()+"", BibliograficMaterial.find(detail.getBibliograficMaterialId()).getTitle(), 
						deadLine, detail.getTicket()+""});
			}
		}
	}
	public HashSet<LoanDetail> getLoanDetails(){
		if(loanDetails == null){
			loanDetails= new HashSet<LoanDetail>();
		}
		return loanDetails;
	}
	public Loan getCurrentLoan(){
		return currentLoan;
	}
	public void setCurrentLoan(Loan currentLoan){
		this.currentLoan= currentLoan;
	}
	public int getItemLimit(){
		return itemLimit;
	}
	public void setItemLimit(int itemLimit){
		this.itemLimit= itemLimit;
	}
}