package ua.edu.sumdu.j2se.pohorila.cli;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.pohorila.notification.ScreenNotification;
import ua.edu.sumdu.j2se.pohorila.tasks.*;
import ua.edu.sumdu.j2se.pohorila.notification.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LoadManager {
	static Scanner in = new Scanner(System.in);
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
	LinkedTaskList linkedTaskList = (LinkedTaskList) TaskListFactory.createTaskList(ListTypes.types.LINKED);
	public static String mail;
	private static Logger log = Logger.getLogger(LoadManager.class.getName());

	/**
	 * Method for loading interface.
	 * @throws IOException
	 */
	public void loader() throws IOException {
		File file = new File("src//res.txt");
		if(file.length()!=0 && linkedTaskList.size() == 0) {
			TaskIO.readText(linkedTaskList, file);
		}
		System.out.println("Hello, it`s your Task Manager");
		if (linkedTaskList.size() == 0) {
			System.out.println("Your task list is empty now");
		} else {
			System.out.println("Your task list:");
			for (Task task : linkedTaskList) {
				System.out.println(task.toString());
			}
		}
		choice();
	}

	/**
	 * Method for giving a choice to the user.
	 * @throws IOException
	 */
	protected void choice() throws IOException {
		int action = 0;
		System.out.print("What do you want? (enter tne number)\n" +
			"1. add task\n" +
			"2. edit task\n" +
			"3. delete task\n" +
			"4. show task list\n" +
			"5. calendar\n" +
			"6. edit notification\n");

		action = in.nextInt();

		switch (action) {
			case 1:
				add();
				break;
			case 2:
				edit();
				break;
			case 3:
				delete();
				break;
			case 4:
				showList();
				break;
			case 5:
				showCalendar();
				break;
			case 6:
				chooseNotification();
				break;
			default:
				System.out.println("It`s wrong input date");
				log.error("Wrong input");
				choice();
		}
	}

	/**
	 * Method for printing task list.
	 * @throws IOException
	 */
	protected void showList() throws IOException {
		if(linkedTaskList.size() <= 0){
			System.out.println("Your list is empty now");
			log.info("Empty list");
		}else {
			for (Task task: linkedTaskList) {
				System.out.println(task.toString());
			}
			log.info("List output");
		}
		loader();
	}

	/**
	 * Method for printing calendar of tasks for a certain period/.
	 * @throws IOException
	 */
	protected void showCalendar() throws IOException {
		System.out.println("Enter date of start in format yyyy.MM.dd HH:mm:");
		LocalDateTime start = enterDateTime();
		System.out.println("Enter date of end in format yyyy.MM.dd HH:mm:");
		LocalDateTime end = enterDateTime();
		if (!start.isAfter(end)) {
			SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(linkedTaskList, start, end);
			for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
				LocalDateTime date = entry.getKey();
				System.out.println("Date: " + date);
				for (Task task : entry.getValue()) {
					System.out.println("\tTask: " + task.getTitle());
				}
			}
		}else{
			System.out.println("It`s wrong date");
			log.error("Wrong input");
		}
		log.info("It`s calendar");
		loader();
	}

	/**
	 * Method for editing tasks.
	 * @throws IOException
	 */
	protected void edit() throws IOException {
		File file = new File("src//res.txt");
		System.out.println("Enter number of task to edit");
		int i = in.nextInt();
		System.out.print("What fo you want to edit? (enter tne number)\n" +
			"1. title\n" +
			"2. time\n" +
			"3. start time\n" +
			"4. end time\n" +
			"5. interval\n" +
			"6. active");
		int t = in.nextInt();
		Task task = linkedTaskList.getTask(i - 1);
		if(t == 1) {
			System.out.println("Enter title");
			String temp = in.nextLine();
			task.setTitle(temp);
		}else if(t == 2){
			System.out.println("Enter date in format yyyy.MM.dd HH:mm:");
			task.setTime(enterDateTime());
		}else if(t == 3){
			System.out.println("Enter start date in format yyyy.MM.dd HH:mm:");
			task.setTime(enterDateTime(), task.getEndTime(), task.getRepeatInterval());
		}else if(t == 4){
			System.out.println("Enter end date in format yyyy.MM.dd HH:mm:");
			task.setTime( task.getStartTime(), enterDateTime(), task.getRepeatInterval());
		}else if(t == 5){
			System.out.println("Enter interval");
			task.setTime( task.getStartTime(), task.getEndTime(), in.nextInt());
		}else if(t==6){
			System.out.println("Enter active(True) or inactive(False)");
			task.setActive(in.nextBoolean());
		}else{
			log.info("Task wasn`t edited");
		}
		file.delete();
		TaskIO.writeText(linkedTaskList, file);
		loader();
	}

	/**
	 * Method for deleting tasks.
	 * @throws IOException
	 */
	protected void delete() throws IOException {
		File file = new File("src//res.txt");
		System.out.println("Enter number of task to delete");
		int i = in.nextInt();
		System.out.println("Do you really want to delete task " + i + "? (1 if yes, or smth else if no)");
		int t = in.nextInt();
		if(t == 1) {
			linkedTaskList.remove(linkedTaskList.getTask(i - 1));
			log.info("Task was deleted");
		}else{
			log.info("Task wasn`t deleted");
		}
		file.delete();
		TaskIO.writeText(linkedTaskList, file);
		loader();
	}

	/**
	 * Method for adding tasks.
	 * @throws IOException
	 */
	protected void add() throws IOException {
		File file = new File("src//res.txt");
		Task temp = new Task(" ",  LocalDateTime.now());
		int t;
		temp.setTime(LocalDateTime.now(), LocalDateTime.now(), 0);
		System.out.println("Enter title");
		String title;
		in.nextLine();
		title = in.nextLine();
		temp.setTitle(title);
		System.out.println("Is it repeated action? (enter 1, if yes, or 0, if not)");
		t = in.nextInt();
		if(t == 0){
			System.out.println("Enter date in format yyyy.MM.dd HH:mm:");
			temp.setTime(enterDateTime());
		}else if(t == 1){
			LocalDateTime s;
			LocalDateTime tt;
			int i;
			System.out.println("Enter start date in format yyyy.MM.dd HH:mm:");
			s = enterDateTime();
			temp.setTime(s);
			System.out.println("Enter end date in format yyyy.MM.dd HH:mm:");
			tt = enterDateTime();
			temp.setTime(tt);
			System.out.println("Enter interval");
			i = in.nextInt();
			temp.setTime(s, tt, i);
		}else{
			System.out.println("It`s wrong input");
			add();
			log.error("Wrong input of repeated");
		}
		linkedTaskList.add(temp);
		log.info("Task was added");
		file.delete();
		TaskIO.writeText(linkedTaskList, file);
		loader();
	}

	/**
	 * Method for entering date from console.
	 * @return
	 */
	private static LocalDateTime enterDateTime() {
		while (in.hasNext()) {
			String date = in.nextLine();
			if (date.isEmpty()) {
				continue;
			}
			try {
				LocalDateTime parse = LocalDateTime.parse(date, formatter);
				log.info("Date entered");
				return parse;
			} catch (Exception ex) {
				System.out.println("Invalid date format");
				log.error("It`s a problem" + ex);
				return enterDateTime();
			}
		}
		System.out.println("Please enter date.");
		return enterDateTime();
	}

	/**
	 * Method for mail or screen notification.
	 * @throws IOException
	 */
	protected void chooseNotification() throws IOException {
		System.out.println("Do you want to receive notifications? (y - if yes, n - if no)");
		in.nextLine();
		String action = in.nextLine();
		if(action.equals("y")){
			System.out.println("Do you want to receive mail or screen notifications? (m - if mail, s - if screen)");
			String action1 = in.nextLine();
			if(action1.equals("m")){
				MailNotification m = new MailNotification();
				System.out.println("Enter your mail: ");
				mail = in.nextLine();
				Runnable notify = new Runnable() {
					@Override
					public void run() {
						synchronized (m) {
							m.notify();
						}
					}
				};
				Thread thread = new Thread(notify);
				thread.setDaemon(true);
				thread.start();
				log.info("Choosen mail notification");
			}
			else if(action1.equals("s")){
				ScreenNotification s = new ScreenNotification();
				Runnable notify = new Runnable() {
					@Override
					public void run() {
						synchronized (s) {
							s.notify();
						}
					}
				};
				Thread thread = new Thread(notify);
				thread.setDaemon(true);
				thread.start();
				log.info("Choosen screen notification ");
			}
			else{
				System.out.println("It`s wrong input");
				log.error("Wrong input in chooseNotification()");
			}
		}
		loader();
	}
}
