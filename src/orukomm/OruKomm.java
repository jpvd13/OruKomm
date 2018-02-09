package orukomm;

import javax.swing.UIManager;
import static org.quartz.DateBuilder.todayAt;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import orukomm.gui.MainWindow;
import orukomm.data.DataInitializer;
import orukomm.logic.EmailJob;

public class OruKomm {

    public static void main(String[] args) throws SchedulerException {
        new DataInitializer();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Create the main window.
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow();
        });

        JobDetail jobDetail = JobBuilder.newJob(EmailJob.class).build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("EmailTrigger")
                .startAt(todayAt(12, 0, 0)) // Email job fires at 12:00:00 every day.
                .withSchedule(simpleSchedule()
                    .withIntervalInHours(24)
                    .repeatForever())
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}