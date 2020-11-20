package ua.edu.sumdu.j2se.pohorila.tasks;
import java.util.Arrays;

/**
 * Class ArrayTaskList.
 * @author Pohorila
 * @version 1.0
 *
 */

public class ArrayTaskList {
	/**Declaration list of tasks.*/
	private Task[] tasks = new Task[15];

	/**Method that returns the number of non-null tasks.
	 * @return amount of tasks*/
	public int size(){
		int s = 0;
		if(tasks != null){
			for (Task task : tasks) {
				if (task != null) {
					s++;
				}
			}
		}
		return s;
	}

	/**Method that add the task to task list and allocates memory if it`s not enough.*/
	public void add(Task task){
		for(int i = 0; i < tasks.length; i++) {
			if(tasks[i] == null){
				tasks[i] = task;
				break;
			}
		}
		if(tasks[tasks.length-1] != null){
			tasks = Arrays.copyOf(tasks, (int)(tasks.length*1.5));
		}
	}


	/**Method that remove the task from task list and reduces the amount of memory if it is not enough.
	 *@return a value indicating whether the event was deleted or not*/
	public boolean remove(Task task){
		if(task == null){
			return false;
		}
		boolean isRemove = false;
		for(int i = 0; i < tasks.length; i++){
			if(tasks[i].equals(task)){
				tasks[i] = null;
				for (int j = i; j < size(); j++){
					tasks[j] = tasks[j+1];
				}
				isRemove = true;
				break;
			}
		}
		if(tasks.length*0.75 > size()){
			tasks = Arrays.copyOf(tasks, (int)(tasks.length*0.85));
		}
		return isRemove;
	}

	/**Method that returns task from the index
	 * @param index index of necessary task
	 * @return needed task or null if index < 0*/
	public Task getTask(int index){
		if(index < 0){
			return null;
		}
		else{
			return tasks[index];
		}
	}

	/**Method that returns list of incoming tasks.
	 * @param from start interval time
	 * @param to  end interval time
	 * @return list of incoming tasks*/
	public ArrayTaskList incoming(int from, int to){
		ArrayTaskList incomingTasks = new ArrayTaskList();
		for(int i = 0; i < size(); i++){
			if (tasks[i].nextTimeAfter(from) > from && tasks[i].nextTimeAfter(from) <= to)
				incomingTasks.add(tasks[i]);
		}
		return incomingTasks;
	}
}
