package orukomm.logic.scheduler.jobs;

import java.io.File;
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
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
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
            // New posts exists: send email to recipients.
            recipients = new ArrayList<>();
            recipients = userRepo.getUsersWithAggregatedNotifications(true);

            // Create email.
            String heading = "Daily notification summary";
            String body = String.format("<h1>Notifications %s</h1>\n", today);
            for (Post post : newPosts) {
                // Only show max 100 chars of each new post.
                if (post.getDescription().length() > 100) {
                    post.setDescription(post.getDescription().substring(0, 100));
                }

                body += String.format("<p>%s %s postade i <i>%s</i>:</p>\n<p>%s</p>\n<br />\n",
                        post.getPosterUser().getFirstName(), post.getPosterUser().getSurname(),
                        post.getCategoryCategory().getCategory(), post.getDescription());
            }

            email = new Email();
            email.send(heading, body, recipients);
            System.out.println("Email notifications sent.");
            
            // Update date for sent notifications.
            fio.write(LAST_CRON_JOB_FILE, today);
        } else {
            System.out.println(String.format("No new feed items since %s: not sending any notifications.", jobLastRan));
        }
    }
}