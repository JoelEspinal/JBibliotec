package com.joel.models;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joel.connections.AbstractConnection;

public class Person extends AbstractModel{

	private String name;
	private String lastName;
	private String idCard;
	private Date dob;
	private Gender gender;
	private String nationality;
	private String phoneNumber;
	private String mobileNumber;
	private String city;
	private String mainAddress;
	private String alternativeAddress;
	private String zipCode;
	
	
	public Person(){
		
	}
	
	public Person(String name, String lastName, Date dob, Gender gender, String nationality, String city, String mainDirection, String zipCode, String idCard){
		this(name,lastName, dob, gender, nationality, city, mainDirection, zipCode);
		this.idCard= idCard;
	}
	public Person(String name, String lastName, Date dob, Gender gender, String nationality, String city, String mainDirection, String zipCode, String idCard,  String phoneNumber){
		this(name,lastName, dob, gender, nationality, city, mainDirection, zipCode, "");
		this.phoneNumber= phoneNumber;
	}
	public Person(String name, String lastName, Date dob, Gender gender, String nationality, String city, String mainDirection, String zipCode, String idCard,  String phoneNumber, String mobileNnumber){
		this(name,lastName, dob, gender, nationality, city, mainDirection, zipCode, phoneNumber);
		this.mobileNumber= mobileNnumber;
	}
	public Person(String name, String lastName, Date dob, Gender gender, String nationality, String city, String mainDirection, String zipCode) {
		this(name,lastName, dob, gender, nationality, city, mainDirection, zipCode, "", "", "", "");
	}
	public Person(String name, String lastName, Date dob, Gender gender, String nationality, String city, String mainDirection, String zipCode, String idCard, String phoneNumber, String mobileNnumber, String alternativeDirection) {
		this.name= name;
		this.lastName= lastName;
		this.dob= dob;
		this.gender= gender;
		this.nationality= nationality;
		this.city= city;
		this.mainAddress= mainDirection;
		this.zipCode= zipCode;
		this.idCard= idCard;
		this.phoneNumber= phoneNumber;
		this.mobileNumber= mobileNnumber;
		this.alternativeAddress= alternativeDirection;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMainAddress() {
		return mainAddress;
	}
	public void setMainAddress(String mainDirection) {
		this.mainAddress = mainDirection;
	}
	public String getAlternativeAddress() {
		return alternativeAddress;
	}
	public void setAlternativeaAddress(String alternativeDirecction) {
		this.alternativeAddress = alternativeDirecction;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public boolean save() {
		String sql= "";
		String date= new SimpleDateFormat("yyyy-MM-dd").format(dob);
		if(id == 0) sql= "insert into persons(name, last_name, id_card, dob, gender, nationality, phone_number, mobile_number, city, main_address, alternative_address, zip_code) values('"+name+"', '"+lastName+"', '"+idCard+"', '"+date+"', '"+gender+"', '"+nationality+"', '"+phoneNumber+"', '"+mobileNumber+"', '"+city+"', '"+mainAddress+"', '"+alternativeAddress+"', '"+zipCode+"')";
		else sql= "update persons set name='"+name+"', last_name='"+lastName+"',id_card='"+idCard+"', dob='"+date+"', gender='"+gender+"', nationality='"+nationality+"', phone_number='"+phoneNumber+"', mobile_number='"+mobileNumber+"', city='"+city+"', main_address='"+mainAddress+"', alternative_address='"+alternativeAddress+"', zip_code='"+zipCode+"' where id="+ id;
		try{ 	boolean result= AbstractConnection.getConnection().executeUpdate(sql); if(result && id == 0) afterSave(); return result;}
		
		catch(Exception e){ e.printStackTrace();}
		return false;
	}
	@Override
	public boolean destroy() {
		boolean result;
		try{
			result= AbstractConnection.getConnection().executeUpdate("delete from persons where id=" + id); return result;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static Person find(int id){
		Person person= new Person();
		try{
			ResultSet result= AbstractConnection.getConnection().executeQuery("select * from persons where id='"+id+"'");
			person.id= id;
			person.name= result.getString("name");
			person.lastName= result.getString("last_name");
			person.idCard= result.getString("id_card");
			person.dob= new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("dob")); //result.getDate("dob");
			person.gender= Gender.getValueOf(result.getString("gender"));
			person.nationality= result.getString("nationality");
			person.phoneNumber= result.getString("phone_number");
			person.mobileNumber= result.getString("mobile_number");
			person.city= result.getString("city");
			person.mainAddress= result.getString("main_address");
			person.alternativeAddress= result.getString("alternative_address");
			person.zipCode= result.getString("zip_code");
		}catch(Exception e){e.printStackTrace();}
		return person;
	}
	public static List<Person> getAll(){
		List<Person> persons= new ArrayList<Person>();
		ResultSet result= null;
		try{
			
			result = AbstractConnection.getConnection().executeQuery("select count(id) from persons");
			result.next();
			int count = result.getInt(1);
			System.out.println("Count::::" + count);
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select id from persons limit 1 offset "+i);
				result.next();
				persons.add(find(result.getInt(1)));
			}
		}catch(Exception e){e.printStackTrace();}
		return persons;
	}
	@Override
	public String atributes() {
		return "person [id="+id+" ,name='"+name+"', last_name='"+lastName+"',id_card='"+idCard+"', dob='"+dob+"', gender='"+gender+"', nationality='"+nationality+"', phone_number='"+phoneNumber+"', mobile_number='"+mobileNumber+"', city='"+city+"', region='"+mainAddress+"', street='"+alternativeAddress+"', house_number='"+zipCode+"']";
	}
	public static List<Client> getClients(){
		List<Client> clients= new ArrayList<Client>();
		ResultSet result= null;
		try{
			result = AbstractConnection.getConnection().executeQuery("select count(id) from clients");
			result.next();
			int count = result.getInt(1);
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select id from clients limit 1 offset "+i);
				result.next();
				clients.add(Client.find(result.getInt(1)));
			}
		}catch(Exception e){e.printStackTrace();}
		return clients;
	}
	public static List<Employee> getEmployees(){
		List<Employee> employees= new ArrayList<Employee>();
		ResultSet result= null;
		try{
			result = AbstractConnection.getConnection().executeQuery("select count(id) from employees");
			result.next();
			int count = result.getInt(1);
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select id from employees limit 1 offset "+i);
				result.next();
				employees.add(Employee.find(result.getInt(1)));
			}
		}catch(Exception e){e.printStackTrace();}
		return employees;
	}
	@Override
	public void afterSave() {
		try{
			ResultSet result= AbstractConnection.getConnection().executeQuery("select max(id) from persons");
			if(result != null) id= result.getInt(1);
		}catch(Exception e){e.printStackTrace();}
	}
	@Override
	public boolean equals(Object obj) {
		return (!isNew() && id ==((Person)obj).id);
	}
}
