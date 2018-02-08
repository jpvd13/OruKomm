package orukomm.logic;

import java.util.ArrayList;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import orukomm.data.entities.User;
import orukomm.data.repositories.UserRepository;

/*
 * Job that sends notification emails of new posts published in the orukomm feed.
 */
public class EmailJob implements Job {

    private UserRepository userRepo;
    private ArrayList<User> recipients;
    private Email email;
    
    public EmailJob() {
        userRepo = new UserRepository();
//        recipients = userRepo.getUsersWithSummedNotifications(); // Retrieve recipients. TODO fix user table
        email = new Email();
    }
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // TODO fetch all new feed items since last email was sent. If any exists: Create
        // heading, body, and send email to recipients.
        
//        email.send(heading, body, recipients);
        System.out.println("Email job ran.");
    }
    
}
