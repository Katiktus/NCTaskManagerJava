package ua.edu.sumdu.j2se.pohorila.tasks;
/**
 * Class AbstractTaskList.
 * @author Pohorila
 * @version 1.0
 *
 */

abstract class AbstractTaskList {
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
		for(int i = 0; i < size(); i++){
			if (getTask(i).nextTimeAfter(from) > from && getTask(i).nextTimeAfter(from) <= to)
				incomingTasks.add(getTask(i));
		}
		return incomingTasks;
	}
}
