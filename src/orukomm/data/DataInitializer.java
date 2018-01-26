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
				+ "`id` int(11) NOT NULL AUTO_INCREMENT, `email` varchar(64) NOT NULL,"
				+ "`first_name` varchar(32) NOT NULL, `surname` varchar(32) NOT NULL,"
				+ "`password_hash` varchar(128) NOT NULL, `salt` varchar(10) DEFAULT NULL,"
				+ "role ENUM('0', '1', '2'), PRIMARY KEY (`id`),"
				+ "UNIQUE KEY `email` (`email`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";

			PreparedStatement psCrt = db.getConnection().prepareStatement(createUserTable);
			psCrt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void seedData() {
		try {
			// Cols: id, email, first_name, surname, password_hash, salt, role.
			String insertUserData = "INSERT INTO user VALUES"
				+ "(1, 'foo@bar.com', 'Foo', 'Bar', 'hejsan', 'HJK(/GHI', '0'),"
				+ "(2, 'foo', 'Baz', 'Quuz', 'foo', 'HJK8/g&%', '0')";

			PreparedStatement ps = db.getConnection().prepareStatement(insertUserData);
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
