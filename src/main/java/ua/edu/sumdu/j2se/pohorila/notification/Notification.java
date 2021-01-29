package ua.edu.sumdu.j2se.pohorila.notification;

import ua.edu.sumdu.j2se.pohorila.tasks.AbstractTaskList;

import java.awt.*;
import java.net.MalformedURLException;

public interface Notification{
		public void notify(AbstractTaskList tasks) throws MalformedURLException, AWTException;
}
