package com.joel.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.joel.connections.AbstractConnection;

public class BibliograficMaterial extends AbstractModel{

	private String title;
	private Date publicationDate;
	private Type type;
	private double penalty;
	
	public BibliograficMaterial(){
		
	}
	public BibliograficMaterial(String title, Date publicationDate, Type type, double penalty) {
		super();
		this.title = title;
		this.publicationDate = publicationDate;
		this.type = type;
		this.penalty= penalty;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	@Override
	public boolean save() {
		String sql= "";
		String date= new SimpleDateFormat("yyyy-MM-dd").format(publicationDate);
		if(id ==0) sql= "insert into bibliografic_material(title, publication_date, type) values('"+title+"', '"+date+"','"+type+"')";
		else sql= "update bibliografic_material set title='"+title+"', publication_date='"+date+"', type='"+type+"' where id="+ id;
		try{
			boolean result= AbstractConnection.getConnection().executeUpdate(sql); if(result && id == 0) afterSave(); return result;
		}catch(Exception e){ e.printStackTrace(); return false;}
	}
	@Override
	public boolean destroy() {
		try{
			boolean result = AbstractConnection.getConnection().executeUpdate("delete from bibliografic_material where id="+id); return result;
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}
	public static BibliograficMaterial find(int id){
		BibliograficMaterial material= new BibliograficMaterial();
		try {
			ResultSet result = AbstractConnection.getConnection().executeQuery("select * from bibliografic_material where id="+ id);
			material.id= id;
			material.title= result.getString("title");
			material.publicationDate= new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("publication_date"));
			material.type= Type.getValueOf(result.getString("type"));
			
		} catch (Exception e) {	e.printStackTrace();}
		return material;
	}
	public static BibliograficMaterial findByTitle(String title){
		BibliograficMaterial material= new BibliograficMaterial();
		try{
			ResultSet result= AbstractConnection.getConnection().executeQuery("select * from bibliografic_material where title='"+title+"'");
			material.id= result.getInt("id");
			material.title= title;
			material.publicationDate= new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("publication_date"));
			material.type= Type.getValueOf(result.getString("type"));
		}catch(Exception e){e.printStackTrace();}
		return material;
	}
	
	public static List<BibliograficMaterial> getAll(){
		List<BibliograficMaterial> materials= new ArrayList<BibliograficMaterial>();
		ResultSet result= null;
		try{
			
			result = AbstractConnection.getConnection().executeQuery("select count(id) from bibliografic_material");
			result.next();
			int count = result.getInt(1);
			
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select id from bibliografic_material limit 1 offset "+i);
				result.next();
				materials.add(find(result.getInt(1)));
			}
		}catch(Exception e){e.printStackTrace();}
		return materials;
	}
	public void addAuthor(Author author){
		try {
			AbstractConnection.getConnection().executeUpdate("insert into author_bibliografic_material(author_id, bibliografic_material_id) values ("+author.getId()+", "+this.id+")");
		} catch (Exception e) {e.printStackTrace();}
	}
	public void removeAuthor(Author author){
		try {
			AbstractConnection.getConnection().executeUpdate("delete from author_bibliografic_material where author_id="+author.getId()+" and bibliografic_material_id="+this.id+"");
		} catch (Exception e) {e.printStackTrace();}
	}
	public List<Author> getAuthors(){
		List<Author> authors = new ArrayList<Author>();
		ResultSet result= null;
		try{
			result = AbstractConnection.getConnection().executeQuery("select count(author_id) from author_bibliografic_material where bibliografic_material_id="+ id);
			result.next();
			int count = result.getInt(1);
			for (int i = 0; i < count; i++) {
				result = AbstractConnection.getConnection().executeQuery("select author_id from author_bibliografic_material where bibliografic_material_id="+id+" limit 1 offset "+i);
				result.next();
				authors.add(Author.find((result.getInt(1))));
			}
		}catch(Exception e){e.printStackTrace();}
		return authors;
	}
	@Override
	public String toString() {
		return getTitle();
	}
	@Override
	public int hashCode() {
		return id;
	}
	@Override
	public String atributes() {
		return "Biliografic Material[id="+id+",title='"+title+"', publication Date='"+publicationDate+"', type='"+type+"']";
	}
	@Override
	protected void afterSave() {
		try {
			ResultSet result = AbstractConnection.getConnection().executeQuery("select max(id) from bibliografic_material");
			if(result != null) id = result.getInt(1);
		} catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public boolean equals(Object obj) {
		return ((BibliograficMaterial)obj).id == id;
	}
}
