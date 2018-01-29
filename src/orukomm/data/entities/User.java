package orukomm.data.entities;

import java.util.EnumSet;

/*
 * Models a user account at OruKomm.
 */
public class User implements Entity {

	private int id;
	private String firstName;
	private String surname;
	private String username;
	private String passwordHash;
	private String salt;
	private int role;
	private String email;

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
		return passwordHash;
	}

	public void setPassword(String password) {
		this.passwordHash = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return firstName + " " + surname;
	}

	/*
	 * User permissions represented as a bit field structure.
	 */
	public enum PermissionFlag {
		NONE(1 << 0),
		USER(1 << 1),
		ADMIN(1 << 2),
		SUPERADMIN(1 << 3);

		private final int permissionFlagValue;

		PermissionFlag(int permissionFlagValue) {
			this.permissionFlagValue = permissionFlagValue;
		}

		public int getPermissionFlagValue() {
			return permissionFlagValue;
		}

		/*
		 * Translates numeric permission code into a set of flags.
		 */
		public EnumSet<PermissionFlag> getPermissionFlags(int permissionValue) {
			EnumSet<PermissionFlag> permissionFlags = EnumSet.noneOf(PermissionFlag.class);
			for (PermissionFlag permissionFlag : PermissionFlag.values()) {
				int flagValue = permissionFlag.permissionFlagValue;
				if ((flagValue & permissionValue) == flagValue) {
					permissionFlags.add(permissionFlag);
				}
			}

			return permissionFlags;
		}

		/*
		 * Translates a set of permission flags into a numeric role code.
		 */
		public int getPermissionValue(EnumSet<PermissionFlag> permissions) {
			int value = 0;
			for (PermissionFlag permissionFlag : permissions) {
				value |= permissionFlag.getPermissionFlagValue();
			}

			return value;
		}
	}
}
