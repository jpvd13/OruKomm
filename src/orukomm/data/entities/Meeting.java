package orukomm.data.entities;

import java.sql.Date;
import java.util.ArrayList;

public class Meeting implements Entity {

    private int id;
    private int meetingCaller;
    private String title;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    private ArrayList<User> invitedUsers;
    private ArrayList<Date> timeSuggestions;

    public ArrayList<Date> getTimeSuggestions() {
        return timeSuggestions;
    }

    public void setTimeSuggestions(ArrayList<Date> timeSuggestions) {
        this.timeSuggestions = timeSuggestions;
    }

    public ArrayList<User> getInvitedUsers() {
        return invitedUsers;
    }

    public void setInvitedUsers(ArrayList<User> invitedUsers) {
        this.invitedUsers = invitedUsers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    private String description;

    public int getMeetingCaller() {
        return meetingCaller;
    }

    public void setMeetingCaller(int meetingCaller) {
        this.meetingCaller = meetingCaller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return title;
    }
    
}
