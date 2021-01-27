package ua.edu.sumdu.j2se.pohorila.notification;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.pohorila.tasks.AbstractTaskList;
import java.awt.*;
import java.awt.TrayIcon.*;
import java.net.MalformedURLException;

public class ScreenNotification implements Notification{
	private static Logger log = Logger.getLogger(ScreenNotification.class.getName());
	@Override
	public void notify(AbstractTaskList tasks) throws MalformedURLException, AWTException {
			if (SystemTray.isSupported()) {
				ScreenNotification td = new ScreenNotification();
				td.display(tasks);
				log.info("It`s screen notification");
			} else {
				System.err.println("System tray not supported!");
				log.error("It`s error while sending notifications");
			}
		}

			public void display(AbstractTaskList tasks) throws AWTException, MalformedURLException {
				SystemTray tray = SystemTray.getSystemTray();
				Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
				TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
				trayIcon.setImageAutoSize(true);
				trayIcon.setToolTip("System tray icon demo");
				tray.add(trayIcon);
				trayIcon.displayMessage("Task list", String.valueOf(tasks), MessageType.INFO);
				log.info("display method of screen notification");
			}
		}
