package com.joel.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joel.connections.AbstractConnection;

public class Loan extends AbstractModel{

	private Date loanDate;
	private int employeeId;
	private int clientId;
	
	public Loan(){
		
	}
	public Loan(Date loanDate, int employeeId, int clientId){
		this.loanDate= loanDate;
		this.employeeId= employeeId;
		this.clientId= clientId;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	@Override
	public boolean save() {
		String sql= "";
		if(id == 0) sql="insert into loans(loan_date, employee_id, client_id) values('"+loanDate+"', '"+employeeId+"', '"+clientId+"')";
		else sql= "update loans set loan_date='"+loanDate+"', employee_id='"+employeeId+"', client_id='"+clientId+"'";
		try{ boolean result = AbstractConnection.getConnection().executeUpdate(sql); if(result && id == 0) afterSave(); return result;
		}catch(Exception e){ e.printStackTrace(); return false;}
	}
	
	@Override
	public boolean destroy() {
		boolean result;
		try{
			result= AbstractConnection.getConnection().executeUpdate("delete from loans where id=" +id); return result;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static Loan find(int id){
		Loan loan= new Loan();
		try{
			ResultSet result = AbstractConnection.getConnection().executeQuery("select * from loans where id="+ id);
			loan.id= result.getInt("id");
			loan.loanDate= result.getDate("loan_date");
			loan.employeeId= result.getInt("employee_id");
			loan.clientId= result.getInt("client_id");
		} catch (Exception e) {	e.printStackTrace();}
		return loan;
	}
	public static List<Loan> getAll(){
		List<Loan> loans= new ArrayList<Loan>();
		ResultSet result= null;
		try{
			result = AbstractConnection.getConnection().executeQuery("select count(id) from loans");
			result.next();
			int count = result.getInt(1);
			
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select id from loans limit 1 offset "+i);
				result.next();
				loans.add(find(result.getInt(1)));
			}
		}catch(Exception e){e.printStackTrace();}
		return loans;
	}
	public List<LoanDetail> getLoanDetails(){
		List<LoanDetail> details= new ArrayList<LoanDetail>();
		ResultSet result= null;
		try {
			result = AbstractConnection.getConnection().executeQuery("select count(loan_details.id) from loan_details where loan_id="+id);
			int count = result.getInt(1);
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select loan_details.id from loan_details where loan_id="+id+" limit 1 offset "+i);
				result.next();
				details.add(LoanDetail.find((result.getInt(1))));
			}
		} catch (Exception e) {e.printStackTrace();}
		return details;
	}
	public void removeLoanDetail(LoanDetail loanDetail){
		try{
			AbstractConnection.getConnection().executeUpdate("delete from loan_details where id="+ loanDetail.getId() + " and loan_id=" + this.id+ "");
		}catch(Exception e){e.printStackTrace();}
	}
	@Override
	public String atributes() {
		return "Loan[id="+id+", loan date='"+loanDate+"', emplooyee_id='"+employeeId+"',client_id='"+clientId+"']";
	}
	@Override
	protected void afterSave() {
		try {
			ResultSet result = AbstractConnection.getConnection().executeQuery("select max(id) from loans");
			if(result != null) id = result.getInt(1);
		} catch (Exception e) {e.printStackTrace();}
	}
	
}
