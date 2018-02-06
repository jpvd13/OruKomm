package orukomm.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static orukomm.data.Database.close;

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
        PreparedStatement ps = null;
        
        try {
            ps = db.getConnection().prepareStatement("DROP TABLE IF EXISTS"
                    + "`attachments`, `meeting_time_suggestion_user`, `meeting_time_suggestion`, `user_meeting`, `meeting`, `posts`, `category`, `user`");
            ps.executeUpdate();

            String createUserTable = "CREATE TABLE `user` ("
                    + "`id` int(11) NOT NULL AUTO_INCREMENT, `first_name` varchar(32) NOT NULL,"
                    + "`surname` varchar(32) NOT NULL, `username` varchar(64) NOT NULL, `email` varchar(128) NOT NULL,"
                    + "`password_hash` varchar(128) NOT NULL, `salt` varchar(16) DEFAULT NULL,"
                    + "role ENUM('2', '6', '14') DEFAULT '2', PRIMARY KEY (`id`),"
                    + "UNIQUE KEY `username` (`username`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";

            ps = db.getConnection().prepareStatement(createUserTable);
            ps.executeUpdate();

            String createCategoryTable = "CREATE TABLE category ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, category VARCHAR(64),"
                    + "PRIMARY KEY (id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            ps = db.getConnection().prepareStatement(createCategoryTable);
            ps.executeUpdate();

            String createPostsTable = "CREATE TABLE posts ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, poster int,"
                    + "title VARCHAR(50) NOT NULL, description TEXT,"
                    + "category int,"
                    + "flow bit,"
                    + "date DATE,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (poster) REFERENCES `user`(`id`),"
                    + "FOREIGN KEY (category) REFERENCES category(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            ps = db.getConnection().prepareStatement(createPostsTable);
            ps.executeUpdate();

            String createAttTable = "CREATE TABLE attachments ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, post_id int,"
                    + "file MEDIUMBLOB,"
                    + "name VARCHAR(100),"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (post_id) REFERENCES posts(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            ps = db.getConnection().prepareStatement(createAttTable);
            ps.executeUpdate();

            // Meeting tables.
            String createMeeting = "CREATE TABLE meeting ("
                    + "id int(11) NOT NULL AUTO_INCREMENT, meeting_caller INT NOT NULL, title VARCHAR(64) NOT NULL,"
                    + "description VARCHAR(512), date DATE NOT NULL,"
                    + "PRIMARY KEY (id), FOREIGN KEY (meeting_caller) REFERENCES user(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            ps = db.getConnection().prepareStatement(createMeeting);
            ps.executeUpdate();

            // Many-to-many relationship table between user and meeting.
            String createUserMeeting = "CREATE TABLE user_meeting ("
                    + "user_id INT(11) NOT NULL, meeting_id INT NOT NULL, attending BIT NOT NULL DEFAULT 0,"
                    + "PRIMARY KEY (user_id, meeting_id), FOREIGN KEY (meeting_id) REFERENCES meeting(id),"
                    + "FOREIGN KEY (user_id) REFERENCES user(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            ps = db.getConnection().prepareStatement(createUserMeeting);
            ps.executeUpdate();

            String createMeetingTimeSuggestion = "CREATE TABLE meeting_time_suggestion ("
                    + "id INT(11) NOT NULL AUTO_INCREMENT, meeting_id INT NOT NULL, time TIME NOT NULL,"
                    + "PRIMARY KEY (id), FOREIGN KEY (meeting_id) REFERENCES meeting(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";

            ps = db.getConnection().prepareStatement(createMeetingTimeSuggestion);
            ps.executeUpdate();

            // Many-to-many realtionship table between a user and time sugggestions.
            String createTimeSuggestionUserTbl = "CREATE TABLE meeting_time_suggestion_user ("
                    + "meeting_time_suggestion_id INT NOT NULL, user_id INT NOT NULL,"
                    + "PRIMARY KEY (meeting_time_suggestion_id, user_id), "
                    + "FOREIGN KEY (meeting_time_suggestion_id) REFERENCES meeting_time_suggestion(id),"
                    + "FOREIGN KEY (user_id) REFERENCES user(id))"
                    + "ENGINE=InnoDB DEFAULT CHARSET=utf8";
            
            ps = db.getConnection().prepareStatement(createTimeSuggestionUserTbl);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

    }

    private void seedData() {
        PreparedStatement ps = null;
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
                    

            ps = db.getConnection().prepareStatement(insertUserData);
            ps.executeUpdate();

            String insertCategoryData = "INSERT INTO category VALUES(null, 'Kul')";

            PreparedStatement ps2 = db.getConnection().prepareStatement(insertCategoryData);
            ps2.executeUpdate();

            String insertPostsData = "INSERT INTO posts VALUES"
                    + " (null , 1, 'Bla', 'Bla', 1, 1, '2008-11-11')";

            PreparedStatement ps3 = db.getConnection().prepareStatement(insertPostsData);
            ps3.executeUpdate();
            
            // Meeting data.
            String insertMeetingsData = "INSERT INTO meeting VALUES (null, 1, 'Ett möte',  'Lorem ipsum', '2018-03-10'),"
                    + "(null, 1, 'Ett annat möte',  'Dolor sit amet.', '2018-03-12'), (null, 3, 'Möte foo',  'Consectetur adipiscing elit.', '2018-03-16'),"
                    + "(null, 2, 'Möte bar',  'Curabitur sed sapien.', '2018-03-25'), (null, 5, 'Möte baz',  'Lobortis, elementum dolor.', '2018-04-21'),"
                     + "(null, 2, 'Möte quuz',  'Rutrum sem.', '2018-04-22'), (null, 5, 'Möte quuz qux',  'Pellentesque viverra, nulla vel posuere vestibulum.', '2018-04-29')";
            
            ps = db.getConnection().prepareStatement(insertMeetingsData);
            ps.executeUpdate();

            String userMeetingData = "INSERT INTO user_meeting VALUES (1, 3, 0), (1, 4, 0), (2, 5, 0), (1, 6, 1),"
                    + "(2, 7, 1), (4, 4, 1), (3, 4, 0), (2, 1, 0), (3, 1, 1), (2, 2, 0), (3, 2, 0), (1, 7, 0)";
            
            ps = db.getConnection().prepareStatement(userMeetingData);
            ps.executeUpdate();
            
            String insertMeetingTimeSuggestions = "INSERT INTO meeting_time_suggestion VALUES "
                    + "(null, 1, '10:30:00'), (null, 1, '12:00:00'), (null, 1, '14:00:00'), (null, 2, '10:00:00'),"
                    + "(null, 2, '12:00:00'), (null, 3, '19:15:00'), (null, 3, '17:15:00'), (null, 4, '10:00:00'),"
                    + "(null, 4, '21:15:00'), (null, 6, '12:00:00'), (null, 6, '15:00:00')";
            
            ps = db.getConnection().prepareStatement(insertMeetingTimeSuggestions);
            ps.executeUpdate();
            
//            String insertTimeSuggestionAttendance = "INSERT INTO ";
            
        } catch (SQLException ex) {
            Logger.getLogger(DataInitializer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }
    }
}
