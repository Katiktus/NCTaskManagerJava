package ua.edu.sumdu.j2se.pohorila.notification;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.pohorila.cli.LoadManager;
import ua.edu.sumdu.j2se.pohorila.tasks.AbstractTaskList;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class MailNotification implements Notification{
	private static Logger log = Logger.getLogger(MailNotification.class.getName());

	/**
	 * Method for email notifications.
	 * @param tasks list of tasks
	 */
	@Override
	public void notify(AbstractTaskList tasks) {
		String to = LoadManager.mail;
		String from = "katpog2001@gmail.com";
		String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject("Ping");
			message.setText("You already have tasks" + String.valueOf(tasks));
			Transport.send(message);
			log.info("Message sent");
		}catch (MessagingException mex) {
			mex.printStackTrace();
			log.error("It`s exception while sending mail" + mex);
		}
	}
}
