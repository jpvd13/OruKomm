package orukomm.logic;

import java.util.ArrayList;
import orukomm.data.entities.User;

public class EmailSender {
    
    public static void send(ArrayList<User> recipients, Email email) {
        // TODO implement actual SMTP-functionality.
    }
    
    public class Email {
        public String heading;
        public String message;
        public ArrayList<User> recipients;

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
    
}
