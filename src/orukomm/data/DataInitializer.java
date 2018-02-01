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
            PreparedStatement psDrp = db.getConnection().prepareStatement("DROP TABLE IF EXISTS `category`, `attachments`, `posts`, `user`");
            psDrp.executeUpdate();

            String createUserTable = "CREATE TABLE `user` ("
                    + "`id` int(11) NOT NULL AUTO_INCREMENT, `first_name` varchar(32) NOT NULL,"
                    + "`surname` varchar(32) NOT NULL, `username` varchar(64) NOT NULL, `email` varchar(128) NOT NULL,"
                    + "`password_hash` varchar(128) NOT NULL, `salt` varchar(16) DEFAULT NULL,"
                    + "role ENUM('2', '6', '14') DEFAULT '2', PRIMARY KEY (`id`),"
                    + "UNIQUE KEY `username` (`username`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";

            PreparedStatement psCrtUser = db.getConnection().prepareStatement(createUserTable);
            psCrtUser.executeUpdate();

            String createPostsTable = "CREATE TABLE posts ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, poster int,"
                    + "title VARCHAR(50) NOT NULL, description TEXT,"
                    + "date DATE,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (poster) REFERENCES `user`(`id`))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            PreparedStatement psCrtPosts = db.getConnection().prepareStatement(createPostsTable);
            psCrtPosts.executeUpdate();

            String createAttTable = "CREATE TABLE attachments ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, post_id int,"
                    + "file MEDIUMBLOB,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (post_id) REFERENCES posts(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";
            
            PreparedStatement psCrtAtts = db.getConnection().prepareStatement(createAttTable);
            psCrtAtts.executeUpdate();
            
            String createCategoryTable = "CREATE TABLE category ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, category VARCHAR(64),"
                    + "PRIMARY KEY (id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";
            
            PreparedStatement psCrtCat = db.getConnection().prepareStatement(createCategoryTable);
            psCrtCat.executeUpdate();            

        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void seedData() {
        try {
            // Cols: id, email, first_name, surname, password_hash, salt, role, email.
            String insertUserData = "INSERT INTO user VALUES"
                    + "(1, 'Foo', 'Bar', 'foo', 'foo@bar.com', 'oEs4nBWAs6OxlaK/oG+bTlBW+LJ1VvuvMFsR7dWg3Dg=', 'Kg+R+prTBxLg3Q==', '14'),"
                    + "(2, 'Bar', 'Baz', 'bar', 'foo@bar.com', 'xZ+21vhC9MOCXqD6xvFuP/N98bVbk3LlJpw0ItS65pg=', 'hDMxhhcEqiG1gw==', '6'),"
                    + "(3, 'Baz', 'Quuz', 'baz', 'foo@bar.com', 'Clv2a/V++MNDfaIylfpxp8b6KvHeK7ts7t3nCGeFv9o=', '8LM/8OOc5zvjew==', '2')";

            PreparedStatement ps = db.getConnection().prepareStatement(insertUserData);
            ps.executeUpdate();
            
            String insertPostsData = "INSERT INTO posts VALUES"

                    + " (null , 1, 'Bla', 'Bla', '2008-11-11')";

            PreparedStatement ps2 = db.getConnection().prepareStatement(insertPostsData);
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
