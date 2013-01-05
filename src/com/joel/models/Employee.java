package com.joel.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joel.connections.AbstractConnection;

public class Employee extends AbstractModel{

	private int personId;
	private String userName;
	private String password;
	
	public Employee(){
		
	}
	public Employee(int personId, String userName, String password){
		this.personId= personId;
		this.userName= userName;
		this.password= password;
	}
	public Employee(Person person, String userName, String password){
		this(person.getId(), userName, password);
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
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public boolean save() {
		String sql= "";
		if(id == 0) sql= "insert into employees(person_id, user_name, password) values("+personId+", '"+userName+"', '"+password+"')";
		else sql= "update employees set person_id="+personId+",  user_name='"+userName+"', password='"+password+"'where id=" +id;
		try{boolean result= AbstractConnection.getConnection().executeUpdate(sql);  if(result && id == 0) afterSave(); return true;}
		catch(Exception e){ e.printStackTrace();}
		return false;
	}
	@Override
	public boolean destroy() {
		boolean result;
		try{
			result= getPerson().destroy() && AbstractConnection.getConnection().executeUpdate("delete from employees where id=" + id); return result;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static Employee find(int id){
		Employee employee= new Employee();
		try{
			ResultSet result= AbstractConnection.getConnection().executeQuery("select * from employees where id="+id+"");
			employee.id= result.getInt("id");
			employee.personId= result.getInt("person_id");
			employee.userName= result.getString("user_name");
			employee.password= result.getString("password");
		}catch(Exception e){e.printStackTrace();}
		return employee;
	}
	public static int validate(String userName, String password){
		int id= 0;
		String sentence= "select id from employees where user_name='"+userName+"' and password='"+password+"'";
		try{
			ResultSet result= AbstractConnection.getConnection().executeQuery(sentence);
			if(result.next()){
				id= result.getInt(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	public static  List<Employee> getAll(){
		List<Employee> employees= new ArrayList<Employee>();
		ResultSet result= null;
		try{
			result = AbstractConnection.getConnection().executeQuery("select count(id) from employees");
			result.next();
			int count = result.getInt(1);
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select id from employees limit 1 offset "+i);
				result.next();
				employees.add(find(result.getInt(1)));
			}
		}catch(Exception e){e.printStackTrace();}		return employees;
	}
	@Override
	public String atributes() {
		return "Employee[id="+id+", user_name='"+userName+"']";
	}
	@Override
	protected void afterSave() {
		try{
			ResultSet result= AbstractConnection.getConnection().executeQuery("select max(id) from employees");
			if(result != null) id= result.getInt(1);
		}catch(Exception e){e.printStackTrace();}
	}
	@Override
	public boolean equals(Object obj) {
		return !isNew() && id == ((Employee)obj).getId();
	}
}
