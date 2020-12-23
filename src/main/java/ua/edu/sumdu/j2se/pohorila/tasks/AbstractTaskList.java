package ua.edu.sumdu.j2se.pohorila.tasks;

import java.util.stream.Stream;

/**
 * Class AbstractTaskList.
 * @author Pohorila
 * @version 1.0
 *
 */

abstract class AbstractTaskList implements Iterable<Task>{
	/**@ListTypes.types type - is it array or linked list
	 * */
	public ListTypes.types types;
	public abstract int size();
	public abstract Task getTask(int i);
	public abstract void add(Task task);
	public abstract boolean remove(Task task);


	/**@return list of incoming tasks
	 * */
	public AbstractTaskList incoming(int from, int to) {
		AbstractTaskList incomingTasks = TaskListFactory.createTaskList(types);
		int nextTimeAfter = 0;
		for(Task task : this){
			nextTimeAfter = task.nextTimeAfter(from);
			if (nextTimeAfter > from && nextTimeAfter <= to)
			incomingTasks.add(task);
		}
		return incomingTasks;
	}
}