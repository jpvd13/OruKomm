package orukomm.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Initializes test data for the oru_komm database.
 */
public class DataInitializer {

	private final Database db;

	public DataInitializer() {
		db = Database.getInstance();
		createTables();
		seedData();
	}

	private void createTables() {
		try {
			PreparedStatement psDrp = db.getConnection().prepareStatement("DROP TABLE IF EXISTS `user`");
			psDrp.executeUpdate();

			String createUserTable = "CREATE TABLE `user` ("
				+ "`id` int(11) NOT NULL AUTO_INCREMENT, `first_name` varchar(32) NOT NULL,"
				+ "`surname` varchar(32) NOT NULL, `username` varchar(64) NOT NULL,"
				+ "`password_hash` varchar(128) NOT NULL, `salt` varchar(16) DEFAULT NULL,"
				+ "role ENUM('2', '6', '14') DEFAULT '2', PRIMARY KEY (`id`),"
                                + "`email` varchar(128) NOT NULL,"
				+ "UNIQUE KEY `username` (`username`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";

			PreparedStatement psCrt = db.getConnection().prepareStatement(createUserTable);
			psCrt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	private void seedData() {
		try {
			// Cols: id, email, first_name, surname, password_hash, salt, role, email.
			String insertUserData = "INSERT INTO user VALUES"
				+ "(1, 'Foo', 'Bar', 'foo', 'oEs4nBWAs6OxlaK/oG+bTlBW+LJ1VvuvMFsR7dWg3Dg=', 'Kg+R+prTBxLg3Q==', '14', 'oo@bar'),"
				+ "(2, 'Bar', 'Baz', 'bar', 'xZ+21vhC9MOCXqD6xvFuP/N98bVbk3LlJpw0ItS65pg=', 'hDMxhhcEqiG1gw==', '6', 'oo@bar'),"
				+ "(3, 'Baz', 'Quuz', 'baz', 'Clv2a/V++MNDfaIylfpxp8b6KvHeK7ts7t3nCGeFv9o=', '8LM/8OOc5zvjew==', '2', 'oo@bar')";

			PreparedStatement ps = db.getConnection().prepareStatement(insertUserData);
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}