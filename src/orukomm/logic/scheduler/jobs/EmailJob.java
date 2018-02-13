package orukomm.logic.scheduler.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import orukomm.data.entities.Post;
import orukomm.data.entities.User;
import orukomm.data.repositories.PostRepository;
import orukomm.data.repositories.UserRepository;
import orukomm.logic.scheduler.FileIO;

/*
 * Job that sends notification emails of new posts published in the orukomm feed.
 */
public class EmailJob implements Job {

    private static String LAST_CRON_JOB_FILE = "cron.txt";
    private PostRepository postRepo;
    private UserRepository userRepo;
    private ArrayList<Post> newPosts;
    private ArrayList<User> recipients;
    private Email email;
    private FileIO file;

    public EmailJob() {
        postRepo = new PostRepository();
        userRepo = new UserRepository();
        newPosts = new ArrayList<>();

//        recipients = userRepo.getUsersWithSummedNotifications(); // Retrieve recipients. TODO fix user table
//        email = new Email();
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {

        System.out.println("job ran");
    }

    public void executeSimulation() {
        String today = new Date(Calendar.getInstance().getTime().getTime()).toString();
        String jobLastRan = today;
        FileIO fio = new FileIO();
        // Check date of last sent emails.
        if (new File(LAST_CRON_JOB_FILE).exists()) {
            jobLastRan = fio.read(LAST_CRON_JOB_FILE);
        } else {
            try {
                // Create file and write today's date.
                new FileOutputStream(LAST_CRON_JOB_FILE, true).close();
                fio.write(LAST_CRON_JOB_FILE, today);
            } catch (IOException ex) {
                Logger.getLogger(EmailJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Check for new posts in repository.
        newPosts = postRepo.getPostsSince(jobLastRan);

        if (newPosts.size() > 0) {
            // New posts: Create email and send to recipients.
//            recipients = userRepo.getEmailRecipients(); // Create method in user repo; 'til then, use test recipients.
            recipients = new ArrayList<>();
            User u1 = new User();
            User u2 = new User();
            u1.setEmail("ad.solecki@gmail.com");
            u2.setEmail("orukomm2@gmail.com");
            recipients.add(u1);
            recipients.add(u2);

            // Create email.
            String heading = "Daily notification summary";
            String body = String.format("<h1>Notifications %s</h1>\n", today);
            for (Post post : newPosts) {
                // Only show max 100 chars of each post.
                if (post.getDescription().length() > 100) {
                    post.setDescription(post.getDescription().substring(0, 100));
                }

                body += String.format("<p>%s %s postade i <i>%s</i>:</p>\n<p>%s</p>\n<br />\n",
                        post.getPosterUser().getFirstName(), post.getPosterUser().getSurname(),
                        post.getCategoryCategory().getCategory(), post.getDescription());
            }

            email = new Email();
            System.out.println(String.format("%s \n %s", heading, body));

            // Update date for sent notifications.
            fio.write(LAST_CRON_JOB_FILE, today);
        } else {
            System.out.println(String.format("No new feed items since %s.", jobLastRan));
        }
    }
}
