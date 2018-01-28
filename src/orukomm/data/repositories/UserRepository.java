package orukomm.data.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import orukomm.data.DataInitializer;
import orukomm.data.Database;
import orukomm.data.entities.User;

public class UserRepository implements Repository<User> {

	private static Database db;

	public UserRepository() {
		db = Database.getInstance();
	}

	@Override
	public void add(User user) {
		String query = String.format("INSERT INTO user VALUES (null, ?, ?, '%s', ?, 'SALT', '%d')", user.getPassword(), user.getRole());
		
		try {
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getUsername());
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
	 * Checks if a user with specified username exists.
	 */
	public boolean userExists(String username) {
		boolean userExists = false;
		String query = "SELECT username FROM user WHERE username = ?";
		try {
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				userExists = true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return userExists;
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
}