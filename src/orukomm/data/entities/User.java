package orukomm.data.entities;

/*
 * Models a user account at OruKomm.
 */
public class User implements Entity {

	private int id;
	private String firstName;
	private String surname;
	private String username;
	private String password;
	private String salt;
	private int role;

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return firstName + " " + surname;
	}

	public enum Role {
		USER(0),
		ADMIN(1),
		SUPERMAN(2);
		private final int value;
		
		private Role(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
}
