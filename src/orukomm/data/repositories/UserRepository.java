package orukomm.data.repositories;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import orukomm.data.DataInitializer;
import orukomm.data.Database;
import static orukomm.data.Database.close;
import orukomm.data.entities.User;

public class UserRepository implements Repository<User> {

    private static Database db;

    public UserRepository() {
        db = Database.getInstance();
    }

    @Override
    public void add(User user) {
        PreparedStatement ps = null;
        String query = String.format("INSERT INTO user VALUES (null, ?, ?, ?, ?, '%s', '%s', '%d')",
                user.getPassword(), user.getSalt(), user.getRole());

        try {
            ps = db.getConnection().prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }
    }

    @Override
    public void remove(User user) {
        PreparedStatement ps = null;
        String query = String.format("DELETE FROM user WHERE id = '%d'", user.getId());

        try {
            ps = db.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }
    }

    @Override
    public void update(User user) {
        PreparedStatement ps = null;
        String query = String.format("UPDATE user SET first_name = ?, surname = ?, email = ?, password_hash = '%s', salt = '%s' WHERE id = '%d'",
                user.getPassword(), user.getSalt(), user.getId());
        try {
            ps = db.getConnection().prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }
    }

    @Override
    public User getById(int id) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = String.format("SELECT * FROM user WHERE id = '%d'", id);

        try {
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (Database.fetchedRows(rs) == 1) {
                // Username exists.
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setSurname(rs.getString("surname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password_hash"));
                user.setSalt(rs.getString("salt"));
                user.setRole(rs.getInt("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        return user;
    }

    /*
	 * Checks if a user with specified username exists.
     */
    public boolean userExists(String username) {
        boolean userExists = false;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String query = "SELECT username FROM user WHERE username = ?";

        try {
            ps = db.getConnection().prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                userExists = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        return userExists;
    }

    public boolean emailExists(String email) {
        boolean emailExists = false;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String query = "SELECT email FROM user WHERE email = ?";

        try {
            ps = db.getConnection().prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                emailExists = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        return emailExists;
    }

    public User getByUsername(String username) {
        User user = new User();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String query = "SELECT * FROM user WHERE username = ?";
            ps = db.getConnection().prepareStatement(query);

            ps.setString(1, username);
            rs = ps.executeQuery();

            if (Database.fetchedRows(rs) == 1) {
                // Username exists.
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setSurname(rs.getString("surname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password_hash"));
                user.setSalt(rs.getString("salt"));
                user.setRole(rs.getInt("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        return user;
    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String query = "SELECT * FROM user";
            ps = db.getConnection().prepareStatement(query);

            rs = ps.executeQuery();

            // Add users to array list.
            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setSurname(rs.getString("surname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password_hash"));
                user.setSalt(rs.getString("salt"));
                user.setRole(rs.getInt("role"));

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        return users;
    }

    /*
	 * Returns a user object if the username and password matches a row in the database.
     */
    public User login(String username, String password) {
        User user = new User();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String query = "SELECT * FROM user WHERE username = ? AND password_hash = ?";
            ps = db.getConnection().prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

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
                    user.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        return user;
    }
}
