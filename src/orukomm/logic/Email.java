package orukomm.logic;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import orukomm.data.entities.User;

public class Email {

    private String username = "orukomm@gmail.com";
    private String password = "hejsan123";
    public String heading;
    public String message;
    public ArrayList<User> recipients;

    private Session session;

    
    /*
     * Test code for sending email:
 
       ArrayList<User> recipients = new ArrayList<>();
       User user = new User();
       user.setEmail("orukomm2@gmail.com");
       recipients.add(user);

       String heading = "E-mail heading";
       String body = String.format("<h1>I are email</h1><p>Hello, %s</p>", user.getEmail());

       Email email = new Email();
       email.send(heading, body, recipients);
     * 
     */
    
    public Email() {
        Properties properties = new Properties();

        // SMTP sdettings.
        properties.put("mail.smtp.user", "orukomm@gmail.com");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "25");
//        properties.put("mail.debug", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.EnableSSL.enable", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

    }

    public void send(String heading, String body, ArrayList<User> recipients) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients.get(0).getEmail())); // TODO iterate through all recipients.
            message.setSubject(heading);

            message.setContent(body, "text/html; charset=utf8");
            Transport.send(message);
            System.out.println("email sent");

        } catch (MessagingException me) {
            throw new RuntimeException(me);
        }        
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
