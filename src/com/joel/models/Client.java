package com.joel.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.joel.connections.AbstractConnection;

public class Client extends AbstractModel{

	private int personId;
	private int itemLimit;
	private String status;
	
	public Client(){
		
	}
	public Client(int personId, String status){
		this(personId,status, 0);
	}
	
	public Client(int personId, String status, int itemLimit) {
		this.personId = personId;
		this.itemLimit = itemLimit;
		this.status = status;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public Person getPerson() {
		return Person.find(personId);
	}
	public void setPerson(Person person) {
		this.personId = person.getId();
	}
	public int getItemLimit() {
		return itemLimit;
	}
	public void setItemLimit(int itemLimit) {
		this.itemLimit = itemLimit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean save() {
		String sql= "";
		if(id == 0) sql= "insert into clients(person_id, status, item_limit) values("+personId+", '"+status+"', "+itemLimit+")";
		else sql= "update clients set person_id="+personId+", status='"+status+"', item_limit='"+itemLimit+"' where id=" + id;
		try{
			boolean result= AbstractConnection.getConnection().executeUpdate(sql); if(result && id == 0) afterSave(); return result;
		}catch(Exception e){ e.printStackTrace(); return false;}
	}
	@Override
	public boolean destroy() {
		try{
			boolean result = getPerson().destroy() && AbstractConnection.getConnection().executeUpdate("delete from clients where id="+id); return result;
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}
	public static Client find(int id){
		Client client= new Client();
		try{
			ResultSet result= AbstractConnection.getConnection().executeQuery("select * from clients where id=" + id);
			client.id= result.getInt("id");
			client.status= result.getString("status");
			client.personId= result.getInt("person_id");
			client.itemLimit= result.getInt("item_limit");
		} catch (Exception e) {	e.printStackTrace();}
		return client;
	}
	public static List<Client> getAll(){
		List<Client> clients= new ArrayList<Client>();
		ResultSet result= null;
		try{
			result = AbstractConnection.getConnection().executeQuery("select count(id) from clients");
			result.next();
			int count = result.getInt(1);
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select id from clients limit 1 offset "+i);
				result.next();
				clients.add(find(result.getInt(1)));
			}
		}catch(Exception e){e.printStackTrace();}
		return clients;
	}
	@Override
	public String atributes() {
		return "Client[id="+id+", status'"+status+"', items_limit="+itemLimit+"]";
	}

	@Override
	protected void afterSave() {
		try {
			ResultSet result = AbstractConnection.getConnection().executeQuery("select max(id) from clients");
			if(result != null) id = result.getInt(1);
		} catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public boolean equals(Object obj) {
		return ((Client)obj).id == id;
	}
	@Override
	public String toString() {
		return Person.find(personId).getName() + " " + id;
	}
}
