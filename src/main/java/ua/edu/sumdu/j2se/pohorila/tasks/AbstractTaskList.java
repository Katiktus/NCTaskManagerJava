package ua.edu.sumdu.j2se.pohorila.tasks;

import java.util.stream.Stream;

/**
 * Class AbstractTaskList.
 * @author Pohorila
 * @version 1.0
 *
 */

abstract public class AbstractTaskList implements Iterable<Task> {
	/**
	 * @ListTypes.types type - is it array or linked list
	 */
	public ListTypes.types types;

	public abstract int size();

	public abstract Task getTask(int i);

	public abstract void add(Task task);

	public abstract boolean remove(Task task);

	public abstract Stream<Task> getStream();

}
