package ua.edu.sumdu.j2se.pohorila.tasks;

/**Class for creating task list
 * */
public class TaskListFactory{

	public static  AbstractTaskList createTaskList(ListTypes.types type){
		AbstractTaskList list = null;
		switch (type) {
			case LINKED:
				list = new LinkedTaskList();
				return list;
			case ARRAY:
				list = new ArrayTaskList();
				return list;
		}
		return null;
	}
}
