package orukomm.data.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import orukomm.data.Database;
import static orukomm.data.Database.close;
import orukomm.data.entities.Meeting;
import orukomm.data.entities.TimeSuggestion;
import orukomm.data.entities.User;

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
        String query = String.format("INSERT INTO meeting VALUES (null, %d, ?, ?, '%tF')",
                meeting.getMeetingCallerUserId(), meeting.getDate());

        // Insert into `meeting`.
        try {
            ps = db.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, meeting.getTitle());
            ps.setString(2, meeting.getDescription());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                meeting.setId(rs.getInt(1)); // Set the generated id for new row in `meeting`.
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs, ps, null);
        }

        // Insert invited users into `user_meeting`.
        try {
            for (int i = 0; i < meeting.getInvitedUsers().size(); i++) {
                query = String.format("INSERT INTO user_meeting VALUES (%d, %d, 0)",
                        meeting.getInvitedUsers().get(i).getId(), meeting.getId());
                ps = db.getConnection().prepareStatement(query);

                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

        if (meeting.getTimeSuggestions().size() > 0) {
            // Insert time sugesstions into `meeting_time_suggestion`.
            try {
                for (int i = 0; i < meeting.getTimeSuggestions().size(); i++) {
                    query = String.format("INSERT INTO meeting_time_suggestion VALUES (null, '%s', '%s')",
                            meeting.getId(), meeting.getTimeSuggestions().get(i).getTime().toString());

                    ps = db.getConnection().prepareStatement(query);
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                close(null, ps, null);
            }
        }
    }

    /*
     * Upsert a user's time suggestion response.
     */
    public boolean existsTimeSuggestionResponse(int timeSuggestionId, int userId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = String.format("SELECT * FROM meeting_time_suggestion_user WHERE meeting_time_suggestion_id = %d "
                + "AND user_id = %d", timeSuggestionId, userId);

        ps = db.getConnection().prepareStatement(query);
        rs = ps.executeQuery();

        return Database.fetchedRows(rs) > 0;
    }

    /*
     * Update or delete a user's time suggestion response.
     */
    public void updateTimeSuggestionResponse(int timeSuggestionId, int userId, boolean timeSuggestionIsSelected) {
        PreparedStatement ps = null;

        try {
            if (!timeSuggestionIsSelected) {
                if (existsTimeSuggestionResponse(timeSuggestionId, userId)) {
                    // Delete response.
                    String query = String.format("DELETE FROM meeting_time_suggestion_user WHERE meeting_time_suggestion_id = %d "
                            + "AND user_id = %d", timeSuggestionId, userId);
                    ps = db.getConnection().prepareStatement(query);
                    ps.executeUpdate();
                }
            } else if (!existsTimeSuggestionResponse(timeSuggestionId, userId)) {
                // Add response.
                String query = String.format("INSERT INTO meeting_time_suggestion_user VALUES (%d, %d)", timeSuggestionId, userId);
                ps = db.getConnection().prepareStatement(query);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }
    }

    /*
     * Returns all meetings where the user with provided userId is invited to.
     */
    public ArrayList<Meeting> getMeetingInvitations(int userId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Meeting> meetings = new ArrayList<>();

        String query = String.format("SELECT * FROM meeting JOIN user_meeting "
                + "ON user_meeting.meeting_id = meeting.id WHERE user_meeting.user_id = %d "
                + "AND date >= CURDATE() ORDER BY date", userId);

        try {
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            // Create meetings array.
            while (rs.next()) {
                Meeting meeting = new Meeting();
                meeting.setId(rs.getInt("id"));
                meeting.setMeetingCallerUserId(rs.getInt("meeting_caller"));
                meeting.setTitle(rs.getString("title"));
                meeting.setDescription(rs.getString("description"));
                meeting.setDate(rs.getDate("date"));

                // Get time suggestions for meeting.
                ArrayList<TimeSuggestion> timeSuggestions = new ArrayList<>();
                PreparedStatement psTime = null;
                ResultSet rsTime = null;
                String getTimeSuggestions = String.format("SELECT * FROM meeting_time_suggestion WHERE meeting_id = %d", meeting.getId());

                psTime = db.getConnection().prepareStatement(getTimeSuggestions);
                rsTime = psTime.executeQuery();

                while (rsTime.next()) {
                    TimeSuggestion timeSuggestion = new TimeSuggestion();
                    timeSuggestion.setId(rsTime.getInt("id"));
                    timeSuggestion.setMeetingid(rsTime.getInt("meeting_id"));
                    timeSuggestion.setTime(rsTime.getTime("time"));

                    timeSuggestions.add(timeSuggestion);
                }

                meeting.setTimeSuggestions(timeSuggestions);

                meetings.add(meeting);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

        return meetings;
    }

    /*
     * Returns all meetings created by user id.
     */
    public ArrayList<Meeting> getCreatedMeetings(int userId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Meeting> meetings = new ArrayList<>();
        String query = String.format("SELECT * FROM meeting WHERE meeting_caller = %d "
                + "AND date >= CURDATE() ORDER BY date", userId);

        try {
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            // Create meetings array.
            while (rs.next()) {
                Meeting meeting = new Meeting();
                meeting.setId(rs.getInt("id"));
                meeting.setMeetingCallerUserId(rs.getInt("meeting_caller"));
                meeting.setTitle(rs.getString("title"));
                meeting.setDescription(rs.getString("description"));
                meeting.setDate(rs.getDate("date"));

                // Create array of all invited users for current meeting.
                ArrayList<User> invitedUsers = new ArrayList<>();
                PreparedStatement psUsers = null;
                ResultSet rsInvitedUsers = null;
                String getInvitedUsers = String.format("SELECT * FROM user JOIN user_meeting ON "
                        + "user_meeting.user_id = user.id WHERE meeting_id = %d", meeting.getId());

                psUsers = db.getConnection().prepareStatement(getInvitedUsers);
                rsInvitedUsers = psUsers.executeQuery();

                while (rsInvitedUsers.next()) {
                    User invitedUser = new User();
                    invitedUser.setId(rsInvitedUsers.getInt("id"));
                    invitedUser.setFirstName(rsInvitedUsers.getString("first_name"));
                    invitedUser.setSurname(rsInvitedUsers.getString("surname"));
                    invitedUser.setUsername(rsInvitedUsers.getString("username"));
                    invitedUser.setEmail(rsInvitedUsers.getString("email"));
                    invitedUser.setPassword(rsInvitedUsers.getString("password_hash"));
                    invitedUser.setSalt(rsInvitedUsers.getString("salt"));
                    invitedUser.setRole(rsInvitedUsers.getInt("role"));

                    invitedUsers.add(invitedUser);
                }
                meeting.setInvitedUsers(invitedUsers);

                // Get time suggestions for meeting.
                ArrayList<TimeSuggestion> timeSuggestions = new ArrayList<>();
                PreparedStatement psTime = null;
                ResultSet rsTime = null;
                String getTimeSuggestions = String.format("SELECT * FROM meeting_time_suggestion WHERE meeting_id = %d", meeting.getId());

                psTime = db.getConnection().prepareStatement(getTimeSuggestions);
                rsTime = psTime.executeQuery();

                while (rsTime.next()) {
                    TimeSuggestion timeSuggestion = new TimeSuggestion();
                    timeSuggestion.setId(rsTime.getInt("id"));
                    timeSuggestion.setMeetingid(rsTime.getInt("meeting_id"));
                    timeSuggestion.setTime(rsTime.getTime("time"));

                    timeSuggestions.add(timeSuggestion);
                }
                meeting.setTimeSuggestions(timeSuggestions);
                
                meetings.add(meeting);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }

        return meetings;
    }

    public void setMeetingAttendance(int userId, int meetingId, boolean attenting) {
        PreparedStatement ps = null;
        String query = String.format("UPDATE user_meeting SET attending = %b WHERE user_id = %d AND meeting_id = %d", attenting, userId, meetingId);

        System.out.println(query);
        
        try {
            ps = db.getConnection().prepareStatement(query);
//            ps.setInt(1, userId);
//            ps.setInt(2, meetingId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }       
    }

    public void removeTimeSuggestion(int timeSuggestionId, int userId) {
        PreparedStatement ps = null;
        String query = String.format("DELETE FROM meeting_time_suggestion_user WHERE meeting_time_suggestion_id = %d AND user_id = %d", timeSuggestionId, userId);
        
        try {
            ps = db.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
     * Returns bool attending status for provided user at provided meeting.
     */
    public boolean getMeetingAttendance(int userId, int meetingId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int attending = 0;
        String query = String.format("SELECT attending FROM user_meeting WHERE user_id = %d AND meeting_id = %d", userId, meetingId);
        
        try {
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            
            if (Database.fetchedRows(rs) == 1) {
                attending = rs.getInt("attending");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MeetingRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, ps, null);
        }
        
        return attending == 1;
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
