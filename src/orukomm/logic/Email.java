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

    private static String host = "smtp.gmail.com";
    private static int port = 587;
    private String username = "orukomm@gmail.com";
    private String password = "hejsan123";
    private Session session;

    public String heading;
    public String message;
    public ArrayList<User> recipients;

    public Email() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.host", port);

        ArrayList<User> recipients = new ArrayList<>();
        User user = new User();
        user.setEmail("orukomm2@gmail.com");
        recipients.add(user);

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("orukomm2@gmail.com")); // TODO iterate through all recipients.
            message.setSubject("Email heading");

            message.setContent("<h1>Mail from java</h1><p>I are mail</p>", "text/html; charset=utf8");
            Transport.send(message);
            System.out.println("email sent");

        } catch (MessagingException me) {
            throw new RuntimeException(me);
        }
    }

//    public void send(ArrayList<User> recipients) {
//    }
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
