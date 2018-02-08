package orukomm.logic;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Session;
import orukomm.data.entities.User;

public class Email {

    private static String host = "smtp.gmail.com";
    private static int port = 587;
    
    public String heading;
    public String message;
    public ArrayList<User> recipients;

    public Email(String username, String password) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.host", port);
        
        Session session;
//        session = Session.getInstance(properties, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
            
    }

    public void send(ArrayList<User> recipients, Email email) {
        // TODO implement actual SMTP-functionality.
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<User> recipients) {
        this.recipients = recipients;
    }

}
