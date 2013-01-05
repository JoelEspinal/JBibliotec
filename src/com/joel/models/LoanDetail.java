package com.joel.models;

import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.joel.connections.AbstractConnection;

public class LoanDetail extends AbstractModel{

	private double ticket;
	private int loanId;
	private Date deadLine;
	private int bibliograficMaterialId;

	public LoanDetail(){
		
	}
	public LoanDetail(double ticket, int loadId, int bibliograficMaterialId, Date deadLine){
		this(ticket,loadId, bibliograficMaterialId);
		this.deadLine= deadLine;
	} 
	public LoanDetail(double ticket, int loadId, int bibliograficMaterialId) {
		super();
		this.ticket = ticket;
		this.loanId = loadId;
		this.bibliograficMaterialId = bibliograficMaterialId;
	}
	public double getTicket() {
		return ticket;
	}
	public void setTicket(double ticket) {
		this.ticket = ticket;
	}
	public int getLoadId() {
		return loanId;
	}
	public void setLoadId(int loadId) {
		this.loanId = loadId;
	}
	
	public Date getDeadline() {
		return deadLine;
	}

	public void setDeadline(Date deadline) {
		this.deadLine = deadline;
	}

	public int getBibliograficMaterialId() {
		return bibliograficMaterialId;
	}
	public void setBibliograficMaterialId(int bibliograficMaterialId) {
		this.bibliograficMaterialId = bibliograficMaterialId;
	}
	@Override
	public boolean save() {
		String sql= "";
		if(id == 0) sql= "insert into loan_details(loan_id, bibliografic_material_id, ticket, deadline) values("+loanId+", "+bibliograficMaterialId+", '"+ticket+"', '"+deadLine+"')";
		else sql= "update loan_details set loan_id="+loanId+", bibliografic_material_id="+bibliograficMaterialId+", ticket='"+ticket+"', deadline='"+deadLine+"' where id=" +this.id;
		try{ boolean result = AbstractConnection.getConnection().executeUpdate(sql); if(result && id == 0) afterSave(); return result;
		}catch(Exception e){ e.printStackTrace(); return false;}
	}
	@Override
	public boolean destroy() {
		boolean result;
		try{
			result= AbstractConnection.getConnection().executeUpdate("delete from loan_details where id=" +id); return result;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static LoanDetail find(int id){
		LoanDetail loanDetail= new LoanDetail();
		try{
			ResultSet result = AbstractConnection.getConnection().executeQuery("select * from loan_details where id="+ id);
			loanDetail.id= result.getInt("id");
			loanDetail.loanId= result.getInt("loan_id");
			loanDetail.bibliograficMaterialId= result.getInt("bibliografic_material_id");
			loanDetail.ticket= result.getDouble("ticket");
			loanDetail.deadLine= result.getDate("deadline");
		} catch (Exception e) {	e.printStackTrace();}
		return loanDetail;
	}
	public static List<LoanDetail> getAll(){
		List<LoanDetail> details= new ArrayList<LoanDetail>();
		ResultSet result= null;
		try{
			
			result = AbstractConnection.getConnection().executeQuery("select count(id) from loan_details");
			result.next();
			int count = result.getInt(1);
			
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select id from loan_details limit 1 offset "+i);
				result.next();
				details.add(find(result.getInt(1)));
			}
		}catch(Exception e){e.printStackTrace();}
		return details;
	}
	@Override
	public String atributes() {
		return "Loand Detalis[id="+id+", loan_id="+loanId+", bibliografic_material_id="+bibliograficMaterialId+", ticket='"+ticket+"']";
	}
	@Override
	protected void afterSave() {
		try {
			ResultSet result = AbstractConnection.getConnection().executeQuery("select max(id) from loan_details");
			if(result != null) id = result.getInt(1);
		} catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public boolean equals(Object obj) {
		LoanDetail ld= ((LoanDetail)obj);
		return (bibliograficMaterialId == ld.getBibliograficMaterialId() && ld.getId() == id);
	}
	@Override
	public int hashCode() {
		return id;
	}
}
