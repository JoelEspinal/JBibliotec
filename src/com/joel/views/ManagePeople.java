package com.joel.views;

import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.freixas.jcalendar.JCalendarCombo;

import com.joel.models.Client;
import com.joel.models.Employee;
import com.joel.models.Person;
import com.joel.models.Role;
import com.joel.models.Gender;

@SuppressWarnings("serial")
public class ManagePeople extends TemplateView{

	private JTextField personName;
	private JTextField lastName;
	private JTextField idCard;
	private JCalendarCombo dob;
	private JComboBox<Gender> gender;
	private JComboBox<Role> rollSelection;
	private JTextField nationality;
	private JTextField phoneNumber;
	private JTextField mobileNumber;
	private JTextField city;
	private JTextField mainAddress;
	private JTextField zipCode;
	private JTextField alternativeAddress;
	private JTextField userName;
	private JPasswordField password;
	private JPanel savePanel;
	private JPanel editDeletePanel;
	private JComboBox<Role> itemsList;
	
	public ManagePeople(){
		setLayout(null);
		add(getPersonName());
		add(getLastName());
		add(getIdCard());
		add(getDob());
		add(getGender());
		add(getPhoneNumber());
		add(getMobileNumber());
		add(getNationality());
		add(getCity());
		add(getMainAddress());
		add(getAlternativeAddress());
		add(getZipCode());
		add(getRollSelection());
		add(getUserName());
		add(getPassword());
		add(getSavePanel());
		
		add(getLabel("*Name: ", 		new Rectangle(35,  30,  100, 14)));
		add(getLabel("*Last Name:", 	new Rectangle(35,  75,  100, 14)));
		add(getLabel("*ID Card", 		new Rectangle(35,  120, 100, 14)));
		add(getLabel("*Day Of Birth:", 	new Rectangle(35,  165, 100, 14)));
		add(getLabel("*Gender:", 		new Rectangle(225, 30,  100, 14)));
		add(getLabel("Phone Number:", 	new Rectangle(225, 75,  100, 14)));
		add(getLabel("Mobile Number:", 	new Rectangle(225, 120, 100, 14)));
		add(getLabel("*Nationality:", 	new Rectangle(225, 165, 100, 14)));
		add(getLabel("*City:", 			new Rectangle(415, 30,  100, 14)));
		add(getLabel("*Address:", 		new Rectangle(415, 75,  100, 14)));
		add(getLabel("Address 2:", 		new Rectangle(415, 120, 100, 14)));
		add(getLabel("*Zip Code:", 		new Rectangle(415, 165, 100, 14)));
		add(getLabel("Role:", 			new Rectangle(605, 30,  100, 14)));		
		add(getLabel("User Name:", 		new Rectangle(605, 75,  100, 14)));
		add(getLabel("Password:", 		new Rectangle(605, 120, 100, 14)));

		add(getItemList());
		add(getTP());
		add(getEditDeletePanel());
	}
	
	public JTextField getPersonName() {
		if(personName ==null){
			personName= new JTextField();
			personName.setBounds(34, 45, 125, 28);
		}
		return personName;
	}
	public JTextField getLastName() {
		if(lastName ==null){
			lastName= new JTextField();
			lastName.setBounds(34, 90, 125, 28);
		}
		return lastName;
	}
	public JTextField getIdCard() {
		if(idCard ==null){
			idCard= new JTextField();
			idCard.setBounds(34, 135, 125, 28);
		}
		return idCard;
	}
	public JCalendarCombo getDob() {
		if(dob ==null){
			dob= new JCalendarCombo();
			dob.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
			dob.setBounds(34, 180, 125, 28);
		}
		return dob;
	}
	public JComboBox<Gender> getGender() {
		if(gender == null){
			gender= new JComboBox<Gender>();
			for(Gender s : Gender.values()){
				gender.addItem(s);
			}
			gender.setBounds(224, 45, 125, 28);
		}
		return gender;
	}
	public JTextField getPhoneNumber() {
		if(phoneNumber ==null){
			phoneNumber= new JTextField();
			phoneNumber.setBounds(224, 90, 125, 28);
		}
		return phoneNumber;
	}
	public JTextField getMobileNumber() {
		if(mobileNumber ==null){
			mobileNumber= new JTextField();
			mobileNumber.setBounds(224, 135, 125, 28);
		}
		return mobileNumber;
	}
	public JTextField getNationality() {
		if(nationality ==null){
			nationality= new JTextField();
			nationality.setBounds(224, 180, 125, 28);
		}
		return nationality;
	}
	public JTextField getCity() {
		if(city ==null){
			city= new JTextField();
			city.setBounds(414, 45, 125, 28);
		}
		return city;
	}
	public JTextField getMainAddress() {
		if(mainAddress ==null){
			mainAddress= new JTextField();
			mainAddress.setBounds(414, 90, 125, 28);
		}
		return mainAddress;
	}
	public JTextField getAlternativeAddress() {
		if(alternativeAddress ==null){
			alternativeAddress= new JTextField();
			alternativeAddress.setBounds(414, 135, 125, 28);
		}
		return alternativeAddress;
	}
	public JTextField getZipCode() {
		if(zipCode ==null){
			zipCode= new JTextField();
			zipCode.setBounds(414, 180, 125, 28);
		}
		return zipCode;
	}
	public JComboBox<Role> getRollSelection(){
		if(rollSelection == null){
			rollSelection = new JComboBox<Role>();
			rollSelection.setBounds(604, 45, 125, 28);
			for(Role roll : Role.values()) rollSelection.addItem(roll);
		}
		return rollSelection;
	}
	public JTextField getUserName() {
		if(userName ==null){
			userName= new JTextField();
			userName.setBounds(604, 90, 125, 28);
		}
		return userName;
	}
	public JPasswordField getPassword() {
		if(password ==null){
			password= new JPasswordField();
			password.setBounds(604, 135, 125, 28);
		}
		return password;
	}
	public JPanel getSavePanel(){
		if(savePanel == null){
			savePanel=new JPanel();
			savePanel.setBounds(592, 174, 135, 32);
			savePanel.add(getClear());
			savePanel.add(getSave());
		}
		return savePanel;
	}
	
	public JComboBox<Role> getItemList(){
		if(itemsList == null){
			itemsList= new JComboBox<Role>();
			for(Role roll : Role.values()){
				itemsList.addItem(roll);
			}
			itemsList.setBounds(34, 215, 115, 28);
			
		}
		return itemsList;
	}
	public JScrollPane getTP(){
		getScroll().setBounds(34, 241, 700, 196);
		return getScroll();
	}
	public JPanel getEditDeletePanel(){
		if(editDeletePanel == null){
			editDeletePanel=new JPanel();
			editDeletePanel.setBounds(539, 447, 139, 33);
			editDeletePanel.add(getEdit());
			editDeletePanel.add(getDelete());
		}
		return editDeletePanel;
	}
	
	public void fill(Role role){
		getTableModel().setRowCount(0);
		if(role.equals(Role.CLIENT)){
			getTableModel().setColumnIdentifiers(new String[]{"ID", "Name", "Last Name", "Id Card", "DOB", "Genre", "Nationality"});
			List<Client> clients= Person.getClients();
			for(Client client: clients){
				Person person= Person.find(client.getPersonId());
			getTableModel().addRow(new String []{client.getId()+"", person.getName(), person.getLastName(), person.getIdCard(), new SimpleDateFormat("MM/dd/yyyy").format(person.getDob()), person.getGender().toString(), person.getNationality()});
			}
		}
		else if(role.equals(Role.EMPLOYEE)){
			getTableModel().setColumnIdentifiers(new String[]{"ID", "Name", "Last Name", "User Name", "Id Card", "DOB", "Genre", "Nationality"});
			List<Employee> employees= Person.getEmployees();
			for(Employee employee: employees){
				Person person= Person.find(employee.getPersonId());
				getTableModel().addRow(new String []{employee.getId()+"", person.getName(), person.getLastName(), employee.getUserName(), person.getIdCard(), new SimpleDateFormat("MM/dd/yyyy").format(person.getDob()), person.getGender().toString(), person.getNationality()});
				
			}
		}
	}
}
