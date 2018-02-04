package orukomm.data.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Meeting implements Entity {

    private int id;
    private int meetingCallerUserId;
    private String title;
    private Date date;
    private ArrayList<User> invitedUsers;
    private ArrayList<Time> timeSuggestions;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Time> getTimeSuggestions() {
        return timeSuggestions;
    }

    public void setTimeSuggestions(ArrayList<Time> timeSuggestions) {
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

    public int getMeetingCallerUserId() {
        return meetingCallerUserId;
    }

    public void setMeetingCallerUserId(int meetingCaller) {
        this.meetingCallerUserId = meetingCaller;
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
        return String.format("%s [%tF]", title, date);
    }
    
}
