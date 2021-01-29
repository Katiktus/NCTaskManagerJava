package ua.edu.sumdu.j2se.pohorila.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
	/**
	 * Methhod for getting incoming tasks.
	 * @return list of incoming tasks.
	 */
	public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
		AbstractTaskList incomingTasks = TaskListFactory.createTaskList(ListTypes.types.LINKED);
		Iterator<Task> it = tasks.iterator();
		while (it.hasNext()){
			Task task = it.next();
			if ( (task.nextTimeAfter(start) != null) && (task.nextTimeAfter(start).isBefore(end) ||
				task.nextTimeAfter(start).equals(end)) ){
				incomingTasks.add(task);
			}
		}
		return incomingTasks;
	}

	/**
	 * Method for return calendar.
	 * @param tasks list of tasks.
	 * @param start date of start.
	 * @param end date of end.
	 * @return calendar of tasks.
	 */
	public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
		SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
		tasks = Tasks.incoming(tasks, start, end);
		for (Task task: tasks) {
			LocalDateTime date = task.nextTimeAfter(start);
			while ( (date != null) && (date.isBefore(end) || date.equals(end))){
				if (!calendar.containsKey(date)){
					calendar.put(date, new HashSet<>());
				}
				calendar.get(date).add(task);
				date = task.nextTimeAfter(date);
			}
		}
		return calendar;
	}
}
