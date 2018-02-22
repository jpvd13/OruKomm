package orukomm.logic.scheduler.jobs;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import orukomm.data.entities.User;

public class Email {

    private String username = "foo@bar.com";
    private String password = "foobar";
    public String heading;
    public String message;
    public ArrayList<User> recipients;

    private Session session;

    public Email() {
        Properties properties = new Properties();

        // SMTP server settings.
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

    /*
     * Sends an email to all recipients in @param recipients.
     */
    public void send(String heading, String body, ArrayList<User> recipients) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));

        String recipientsCsv = "";
        for (User user : recipients) {
            recipientsCsv += user.getEmail() + ",";
        }

        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientsCsv));
        message.setSubject(heading);
        message.setContent(body, "text/html; charset=utf8");

        Transport.send(message);
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
