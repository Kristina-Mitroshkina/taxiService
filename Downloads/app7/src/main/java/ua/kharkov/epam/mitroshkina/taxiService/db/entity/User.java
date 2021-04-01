package ua.kharkov.epam.mitroshkina.taxiService.db.entity;

/**
 * User entity.
 * 
 * @author D.Kolesnikov
 * 
 */
public class User extends Entity {

	private static final long serialVersionUID = -6889036256149495388L;
	private int user_id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private int phone_number;
	private int role_id;


	public User() {
	}
	public User(String name, String surname, String email, String password, int phone_number, int role_id) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phone_number = phone_number;
		this.role_id=role_id;
	}
	public int getUser_id() {
		return this.user_id;
	}
	public String getName() {
		return this.name;
	}
	public String getSurname() {
		return this.surname;
	}
	public String getEmail() {
		return this.email;
	}
	public int getPhone_number() {
		return this.phone_number;
	}
	public String getPassword() {
		return this.password;
	}
	public int getRole_id() {
		return this.role_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	@Override
	public String toString() {
		return "[" + user_id + "," + name + "," + surname + "," + email + "," + password  + "," + phone_number+ "," + role_id + "]";
	}

}
