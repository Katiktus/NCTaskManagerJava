package ua.edu.sumdu.j2se.pohorila.notification;

import ua.edu.sumdu.j2se.pohorila.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.pohorila.tasks.Task;

import java.awt.*;
import java.net.MalformedURLException;
import java.util.Set;

public interface Notification{
		public void notify(AbstractTaskList tasks) throws MalformedURLException, AWTException;
}
