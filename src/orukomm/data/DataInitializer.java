package orukomm.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Initializes test data for the oru_komm database.
 */
public class DataInitializer {
	private final Database db;
	
	public DataInitializer()
	{
		db = Database.getInstance();
		
		createTables();
		seedData();
		testLogin("foo@bar.com", "hejsan");
	}
	
	private void createTables()
	{
		try {
			PreparedStatement psDrp = db.getConnection().prepareStatement("DROP TABLE IF EXISTS `user`");		
			psDrp.executeUpdate();
			
			String createUserTable = "CREATE TABLE `user` ("
				+ "`id` int(11) NOT NULL, `email` varchar(64) NOT NULL,"
				+ "`first_name` varchar(32) NOT NULL, `surname` varchar(32) NOT NULL,"
				+ "`password_hash` varchar(128) NOT NULL, `salt` varchar(10) DEFAULT NULL,"
				+ "PRIMARY KEY (`id`), UNIQUE KEY `email` (`email`))"
				+ "ENGINE=InnoDB DEFAULT CHARSET=utf8";
			
			PreparedStatement psCrt = db.getConnection().prepareStatement(createUserTable);
			psCrt.executeUpdate();
		}
		catch (SQLException ex)
		{
			Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	private void seedData()
	{
		try
		{
			// Cols: id, email, first_name, surname, password_hash, salt.
			String insertUserData = "INSERT INTO user VALUES ("
					+ "1, 'foo@bar.com', 'Foo', 'Bar', 'hejsan', 'HJK(/GHI'"
					+ ")";
			
			PreparedStatement ps = db.getConnection().prepareStatement(insertUserData);
			ps.executeUpdate();
		}
		catch (SQLException ex)
		{
			Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void testLogin(String username, String password)
	{
		try
		{
			String query = "SELECT * FROM user WHERE email = ? AND password_hash = ?";
			PreparedStatement ps = db.getConnection().prepareStatement(query);

			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				if (rs.getInt(1) == 1)
				{
					// Login successful.
					String resEmail = rs.getString("first_name");
					String resSurname = rs.getString("surname");
					System.out.println("Welcome, " + resEmail + " " + resSurname + "!");
				}
			}
			else {
				System.out.println("Wrong username or password.");
			}
		}
		catch (SQLException ex)
		{
			Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
