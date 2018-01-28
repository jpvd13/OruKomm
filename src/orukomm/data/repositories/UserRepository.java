package orukomm.data.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import orukomm.data.DataInitializer;
import orukomm.data.Database;
import orukomm.data.entities.User;
import orukomm.data.entities.User.PermissionFlag;

public class UserRepository implements Repository<User> {

	private static Database db;

	public UserRepository() {
		db = Database.getInstance();
	}

	@Override
	public void add(User user) {
		String query = String.format("INSERT INTO user VALUES (null, '%s', '%s', '%s', '%s', '%s', 1)",
			user.getFirstName(), user.getSurname(), user.getUsername(), user.getPassword(), "SALT");
		
		PreparedStatement ps;
		try {
			ps = db.getConnection().prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void remove(User user) {
		// SQL to remove user.
	}

	@Override
	public User get(int id) {
		// SQL to fetch User by id.
		return new User();
	}

	/*
	 * Returns a user object if the username and password matches a row in the database.
	 */
	public User login(String username, String password) {
		User user = new User();
		try {
			String query = "SELECT * FROM user WHERE username = ? AND password_hash = ?";
			PreparedStatement ps = db.getConnection().prepareStatement(query);

			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.isFirst() && rs.isLast()) {
					// Login successful: Set logged in user object.
					user.setId(rs.getInt("id"));
					user.setFirstName(rs.getString("first_name"));
					user.setSurname(rs.getString("surname"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password_hash"));
					user.setSalt(rs.getString("salt"));
					user.setRole(rs.getInt("role"));
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
		}

		return user;
	}

	public static void registerUser(JTextField txt1, JTextField txt2, JTextField txt3, JTextField txt4, JPasswordField pw1, JPasswordField pw2)
		throws SQLException {

		String firstName = txt1.getText();
		String lastName = txt2.getText();
		String email = txt3.getText();
		String password1 = pw1.getText();
		String password2 = pw2.getText();

		if (password1.equals(password2)) {
			try {
				String query = "INSERT INTO user VALUES(null, '" + email + "','" + firstName + "','" + lastName + "','" + password1 + "', null," + PermissionFlag.USER.ordinal() + ")";
				System.out.println(query);
				PreparedStatement ps = db.getConnection().prepareStatement(query);
				ps.executeUpdate();

			} catch (SQLException ex) {
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
