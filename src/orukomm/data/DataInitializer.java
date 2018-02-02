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
            PreparedStatement psDrp = db.getConnection().prepareStatement("DROP TABLE IF EXISTS"
                    + "`attachments`, `meeting_time_suggestion`, `user_meeting`, `meeting`, `posts`, `category`, `user`");
            psDrp.executeUpdate();

            String createUserTable = "CREATE TABLE `user` ("
                    + "`id` int(11) NOT NULL AUTO_INCREMENT, `first_name` varchar(32) NOT NULL,"
                    + "`surname` varchar(32) NOT NULL, `username` varchar(64) NOT NULL, `email` varchar(128) NOT NULL,"
                    + "`password_hash` varchar(128) NOT NULL, `salt` varchar(16) DEFAULT NULL,"
                    + "role ENUM('2', '6', '14') DEFAULT '2', PRIMARY KEY (`id`),"
                    + "UNIQUE KEY `username` (`username`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";

            PreparedStatement psCrtUser = db.getConnection().prepareStatement(createUserTable);
            psCrtUser.executeUpdate();

            String createCategoryTable = "CREATE TABLE category ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, category VARCHAR(64),"
                    + "PRIMARY KEY (id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            PreparedStatement psCrtCat = db.getConnection().prepareStatement(createCategoryTable);
            psCrtCat.executeUpdate();

            String createPostsTable = "CREATE TABLE posts ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, poster int,"
                    + "title VARCHAR(50) NOT NULL, description TEXT,"
                    + "category int,"
                    + "date DATE,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (poster) REFERENCES `user`(`id`),"
                    + "FOREIGN KEY (category) REFERENCES category(id))"
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

            // Meeting tables.
            String createMeeting = "CREATE TABLE meeting ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, meeting_caller INT NOT NULL, title VARCHAR(64) NOT NULL,"
                    + "description VARCHAR(512) NOT NULL, date DATE NOT NULL,"
                    + "PRIMARY KEY (id), FOREIGN KEY (meeting_caller) REFERENCES user(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            PreparedStatement psCrtMeeting = db.getConnection().prepareStatement(createMeeting);
            psCrtMeeting.executeUpdate();

            // Many-to-many relationship table between user and meeting.
            String createUserMeeting = "CREATE TABLE user_meeting ("
                    + "user_id INT(11) NOT NULL, meeting_id INT NOT NULL,"
                    + "PRIMARY KEY (user_id, meeting_id), FOREIGN KEY (meeting_id) REFERENCES meeting(id),"
                    + "FOREIGN KEY (user_id) REFERENCES user(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            PreparedStatement psCrtUserMeeting = db.getConnection().prepareStatement(createUserMeeting);
            psCrtUserMeeting.executeUpdate();

            String createMeetingTimeSuggestion = "CREATE TABLE meeting_time_suggestion ("
                    + "id INT(11) NOT NULL, meeting_id INT NOT NULL, time DATE NOT NULL,"
                    + "PRIMARY KEY (id), FOREIGN KEY (meeting_id) REFERENCES meeting(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            PreparedStatement psCrtMeetingTime = db.getConnection().prepareStatement(createMeetingTimeSuggestion);
            psCrtMeetingTime.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void seedData() {
        try {
            // User data.
            String insertUserData = "INSERT INTO user VALUES"
                    + "(1, 'Foo', 'Bar', 'foo', 'foo@bar.com', 'oEs4nBWAs6OxlaK/oG+bTlBW+LJ1VvuvMFsR7dWg3Dg=', 'Kg+R+prTBxLg3Q==', '14'),"
                    + "(2, 'Bar', 'Baz', 'bar', 'foo@bar.com', 'xZ+21vhC9MOCXqD6xvFuP/N98bVbk3LlJpw0ItS65pg=', 'hDMxhhcEqiG1gw==', '6'),"
                    + "(3, 'Baz', 'Quuz', 'baz', 'foo@bar.com', 'Clv2a/V++MNDfaIylfpxp8b6KvHeK7ts7t3nCGeFv9o=', '8LM/8OOc5zvjew==', '2'),"
                    + "(4, 'Anders', 'Anka', 'ankan', 'foo@bar.com', 'Clv2a/V++MNDfaIylfpxp8b6KvHeK7ts7t3nCGeFv9o=', '8LM/8OOc5zvjew==', '2')," // pw == hejsan
                    + "(5, 'Bertil', 'Böna', 'bönan', 'foo@bar.com', 'Clv2a/V++MNDfaIylfpxp8b6KvHeK7ts7t3nCGeFv9o=', '8LM/8OOc5zvjew==', '2')," // ...
                    + "(6, 'Cecilia', 'Citron', 'citronen', 'foo@bar.com', 'Clv2a/V++MNDfaIylfpxp8b6KvHeK7ts7t3nCGeFv9o=', '8LM/8OOc5zvjew==', '2'),"
                    + "(7, 'Daniel', 'Duva', 'duvan', 'foo@bar.com', 'Clv2a/V++MNDfaIylfpxp8b6KvHeK7ts7t3nCGeFv9o=', '8LM/8OOc5zvjew==', '2'),"
                    + "(8, 'Erik', 'Elefant', 'elefanten', 'foo@bar.com', 'Clv2a/V++MNDfaIylfpxp8b6KvHeK7ts7t3nCGeFv9o=', '8LM/8OOc5zvjew==', '2')";
                    

            PreparedStatement ps = db.getConnection().prepareStatement(insertUserData);
            ps.executeUpdate();

            String insertCategoryData = "INSERT INTO category VALUES(null, 'Kul')";

            PreparedStatement ps2 = db.getConnection().prepareStatement(insertCategoryData);
            ps2.executeUpdate();

            String insertPostsData = "INSERT INTO posts VALUES"
                    + " (null , 1, 'Bla', 'Bla', 1, '2008-11-11')";

            PreparedStatement ps3 = db.getConnection().prepareStatement(insertPostsData);
            ps3.executeUpdate();
            
            // Meeting data.
            String insertMeetingsData = "INSERT INTO meeting VALUES (null, 1, 'Ett möte',  'Lorem ipsum', '2018-01-02'),"
                    + "(null, 1, 'Ett annat möte',  'Dolor sit amet.', '2018-01-02'), (null, 3, 'Fooo',  'Consectetur adipiscing elit.', '2018-01-02'),"
                    + "(null, 2, 'Baar',  'Curabitur sed sapien.', '2018-01-02'), (null, 5, 'Baaz',  'Lobortis, elementum dolor.', '2018-01-02'),"
                     + "(null, 2, 'Quuuz',  'Rutrum sem.', '2018-01-02'), (null, 5, 'Quuuz qux',  'Pellentesque viverra, nulla vel posuere vestibulum.', '2018-01-02')";
            
            ps = db.getConnection().prepareStatement(insertMeetingsData);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
