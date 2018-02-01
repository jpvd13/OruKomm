package orukomm.data.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import orukomm.data.Database;
import static orukomm.data.Database.close;
import orukomm.data.entities.Meeting;

public class MeetingRepository implements Repository<Meeting> {

    private Database db;

    public MeetingRepository() {
        db = Database.getInstance();
    }

    /*
     * Insert new rows for tables `meeting`, `user_meeting`, and `meeting_time_suggestion`.
     */
    @Override
    public void add(Meeting meeting) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = String.format("INSERT INTO meeting VALUES (null, %d, ?, ?)", meeting.getMeetingCaller());

        // Insert into `meeting`.
        try {
            ps = db.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, meeting.getTitle());
            ps.setString(2, meeting.getDescription());
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            if (rs.next())
                meeting.setId(rs.getInt(1)); // Set the generated id for new row in `meeting`.
                
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        // Insert invited users into `user_meeting`.
        try {
            for (int i = 0; i < meeting.getInvitedUsers().size(); i++) {
                query = String.format("INSERT INTO user_meeting VALUES (%d, %d)", meeting.getInvitedUsers().get(i).getId(), meeting.getId());
                ps = db.getConnection().prepareStatement(query);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

        // Insert to `meeting_time_suggestion`.
        // TODO implement.
    }

    @Override
    public void remove(Meeting entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Meeting entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Meeting getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
