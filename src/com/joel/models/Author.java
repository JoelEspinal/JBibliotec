package com.joel.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.joel.connections.AbstractConnection;

public class Author extends AbstractModel{

	private String name;
	private String lastName;
	private int blibliograficMaterialId;
	
	public Author(){
		
	}
	public Author(String name, String lastName, int blibliograficMaterialId) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.blibliograficMaterialId = blibliograficMaterialId;
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
	public int getBlibliograficMaterialId() {
		return blibliograficMaterialId;
	}
	public void setBlibliograficMaterialId(int blibliograficMaterialId) {
		this.blibliograficMaterialId = blibliograficMaterialId;
	}
	@Override
	public boolean save() {
		String sql = "";
		if(id == 0) sql= "insert into authors(name, last_name, bibliografic_material_id) values('"+name+"', '"+lastName+"', '"+blibliograficMaterialId+"')";
		else sql= "update authors set name='"+name+"', last_name='"+lastName+"', bibliografic_material_id="+blibliograficMaterialId+" where id=" +id;
		
		try{ boolean result = AbstractConnection.getConnection().executeUpdate(sql); if(result && id == 0) afterSave(); return result;
		}catch(Exception e){ e.printStackTrace(); return false;}
	}
	@Override
	public boolean destroy() {
		boolean result;
		try{
			result= AbstractConnection.getConnection().executeUpdate("delete from authors where id=" +id); return result;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static Author find(int id){
		Author author= new Author();
		try {
			ResultSet result = AbstractConnection.getConnection().executeQuery("select * from authors where id="+ id);
			author.id= result.getInt("id");
			author.name= result.getString("name");
			author.lastName= result.getString("last_name");
			author.blibliograficMaterialId= result.getInt("bibliografic_material_id");
		} catch (Exception e) {	e.printStackTrace();}
		return author;
	}
	public static List<Author> getAll(){
		List<Author> authors = new ArrayList<Author>();
		ResultSet result= null;
		try{
			result = AbstractConnection.getConnection().executeQuery("select count(id) from authors");
			result.next();
			int count = result.getInt(1);
			
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select id from authors limit 1 offset "+i);
				result.next();
				authors.add(find(result.getInt(1)));
			}
		}catch(Exception e){e.printStackTrace();}
		return authors;
	}
	public void addMaterial(BibliograficMaterial material){
		try {
			AbstractConnection.getConnection().executeUpdate("insert into author_bibliografic_material(author_id, bibliografic_material_id) values ("+id+", "+material.getId()+")");
		} catch (Exception e) {e.printStackTrace();}
	}
	public void removeMaterial(BibliograficMaterial material){
		try {
			AbstractConnection.getConnection().executeUpdate("delete from author_bibliografic_material where author_id="+this.id+" and bibliografic_material_id="+material.getId()+"");
		} catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public String atributes() {
		return "Author[id="+id+",name='"+name+"', last_name='"+lastName+"',  bibliografic_material_id="+blibliograficMaterialId+"]";
	}
	@Override
	protected void afterSave() {
		try {
			ResultSet result = AbstractConnection.getConnection().executeQuery("select max(id) from authors");
			if(result != null) id = result.getInt(1);
		} catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public boolean equals(Object obj) {
		return ((Author)obj).id == id;
	}
	@Override
	public String toString() {
		return getName()+" "+getLastName();
	}
}
