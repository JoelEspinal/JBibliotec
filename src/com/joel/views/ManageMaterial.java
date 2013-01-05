package com.joel.views;

import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.freixas.jcalendar.JCalendarCombo;

import com.joel.models.Author;
import com.joel.models.BibliograficMaterial;
import com.joel.models.Type;

@SuppressWarnings("serial")
public class ManageMaterial extends TemplateView{

	private JTextField title;
	private JCalendarCombo publicationDate;
	private JComboBox<Type> type;
	private JPanel savePanel;
	private JPanel editDeletePanel;
	private JList<Author> authorList;
	private DefaultListModel<Author> defaultAuthor;
	private JScrollPane scrollAuthor;
	private JPanel panel;
	private JButton createAuthor;
	private JButton removeAuthor;
	private JButton select;
	private JTextField authorName;
	private JTextField authorLastName;
	private Set<Author> authors;
	private BibliograficMaterial correntMaerial;
	
	public ManageMaterial(){
		setLayout(null);
		add(getTitle());
		add(getPublicationDate());
		add(getType());
		add(getSavePanel());
		add(getEditDeletePanel());
		add(getScrollAuthor());

		add(getLabel("Title:", 				new Rectangle(34, 45, 100, 14)));
		add(getLabel("Type:", 				new Rectangle(34, 95, 100, 14)));
		add(getLabel("Publication Date::", 	new Rectangle(34, 145, 100, 14)));
		add(getPanel());
		add(getTP());
		fillMaterial();
	}
	public JScrollPane getTP(){
		getScroll().setBounds(34, 250, 700, 196);
		return getScroll();
	}
	public JPanel getSavePanel(){
		if(savePanel == null){
			savePanel=new JPanel();
			savePanel.setBounds(34, 214, 152, 33);
			savePanel.add(getClear());
			savePanel.add(getSave());
		}
		return savePanel;
	}
	public JPanel getEditDeletePanel(){
		if(editDeletePanel == null){
			editDeletePanel=new JPanel();
			editDeletePanel.setBounds(547, 462, 139, 33);
			editDeletePanel.add(getEdit());
			editDeletePanel.add(getDelete());
		}		return editDeletePanel;
	}
	public JTextField getTitle() {
		if(title == null){
			title= new JTextField();
			title.setBounds(34, 64, 141, 25);
		}
		return title;
	}
	public JCalendarCombo getPublicationDate() {
		if(publicationDate == null){
			publicationDate= new JCalendarCombo();
			publicationDate.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
			publicationDate.setBounds(34, 167, 141, 33);
		}
		return publicationDate;
	}
	public JComboBox<Type> getType() {
		if(type == null){
			type= new JComboBox<Type>();
			for(Type t : Type.values()){
				type.addItem(t);
			}
			type.setBounds(34, 111, 141, 23);
		}
		return type;
	}
	public JScrollPane getScrollAuthor(){
		if(scrollAuthor == null){
			scrollAuthor= new JScrollPane(getAuthorList());
			scrollAuthor.setBounds(360, 64, 326, 126);
		}
		return scrollAuthor;
	}
	public JList<Author> getAuthorList(){
		if(authorList == null){
			authorList= new JList<Author>(getDefaultAuthor());
		}
		return authorList;
	}
	
	public DefaultListModel<Author> getDefaultAuthor(){
		if(defaultAuthor == null){
			defaultAuthor= new DefaultListModel<Author>();
		}
		return defaultAuthor;
	}
	public void authorFill(){
		getDefaultAuthor().clear();
		if(getCurrrentMaterial() != null){
			for(Author author : getCurrrentMaterial().getAuthors()){
				getDefaultAuthor().addElement(author);
			}
		}
		for(Author author : getAuthors()){
			getDefaultAuthor().addElement(author);
		}
	}
	public void fillMaterial(){
		getTableModel().setRowCount(0);
		getTableModel().setColumnIdentifiers(new String []{"Id", "Title", "Type", "Publication Date"});
		for(BibliograficMaterial material : BibliograficMaterial.getAll()){
			getTableModel().addRow(new String[]{material.getId()+ "", material.getTitle(), material.getType().toString(), new SimpleDateFormat("MM/dd/yyyy").format(material.getPublicationDate())});
		}
	}
	public JButton getAddAuthor(){
		if(createAuthor == null){
			createAuthor= new JButton("Create");
			
		}
		return createAuthor;
	}
	public JButton getRemoveAuthor(){
		if(removeAuthor == null){
			removeAuthor= new JButton("Remove");
		}
		return removeAuthor;
	}
	public JButton getSelect(){
		if(select == null){
			select= new JButton("Select");
		}
		return select;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getSelect());
			panel.add(getAddAuthor());
			panel.add(getRemoveAuthor());
			panel.setBounds(407, 201, 279, 33);
		}
		return panel;
	}
	public JTextField getAuthorName(){
		if(authorName == null){
			authorName= new JTextField(20);
		}
		return authorName;
	}
	public JTextField getAuthorLastName(){
		if(authorLastName == null){
			authorLastName= new JTextField(20);
		}
		return authorLastName;
	}
	public Set<Author> getAuthors(){
			if(authors == null){
				authors= new HashSet<Author>();
			}
			return authors;
	}
	public BibliograficMaterial getCurrrentMaterial(){
		return correntMaerial;
	}
	public void setCurrrentMaterial(BibliograficMaterial correntMaerial){
		this.correntMaerial= correntMaerial;
	}
}
