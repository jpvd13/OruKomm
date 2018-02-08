package orukomm;

import java.sql.Date;
import javax.swing.UIManager;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
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
                
                // Email job scheduler.
                JobDetail jobDetail = new JobDetail();
                jobDetail.setName("EmailJob");
                jobDetail.setJobClass(EmailJob.class);
                
                SimpleTrigger trigger = new SimpleTrigger();
                trigger.setName("EmailTrigger");
                trigger.setStartTime(new Date(System.currentTimeMillis()));
                trigger.setRepeatInterval(10000);
                trigger.setRepeatCount(trigger.REPEAT_INDEFINITELY);
                
                Scheduler scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.scheduleJob(jobDetail, trigger);
                scheduler.start();
        }
}
