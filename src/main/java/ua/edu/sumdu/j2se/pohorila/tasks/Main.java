package ua.edu.sumdu.j2se.pohorila.tasks;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		Task test = new Task("Course", 10);
		Task test2 = new Task("Course2", 5, 9, 24);
		Task test3 = new Task("Course3", 5, 9, 24);
		Task test5 = new Task("Course5", 5, 9, 24);
		Task test6 = new Task("Course6", 5, 9, 24);
		LinkedTaskList linkedTaskList = (LinkedTaskList) TaskListFactory.createTaskList(ListTypes.types.LINKED);
		linkedTaskList.add(test3);
		linkedTaskList.add(test2);
		linkedTaskList.add(test5);
		linkedTaskList.add(test6);

		LinkedTaskList link = (LinkedTaskList) linkedTaskList.incoming(6,7);
		System.out.println(link.getClass());
	}

}
