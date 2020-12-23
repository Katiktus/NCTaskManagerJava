package ua.edu.sumdu.j2se.pohorila.tasks;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

/**
 * Class ArrayTaskList.
 * @author Pohorila
 * @version 1.0
 *
 */

public class ArrayTaskList extends AbstractTaskList implements Cloneable{
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
			if(this.tasks[i].equals(task)){
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
	public Task getTask(int index) throws IndexOutOfBoundsException{
		if(index < 0 || index > tasks.length){
			throw new IndexOutOfBoundsException("index > size of array or < 0");
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

	@Override
	public Iterator<Task> iterator() {
		Iterator<Task> it = new Iterator<Task>() {
			private int last = -1;
			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < tasks.length && tasks[currentIndex] != null;
			}

			@Override
			public Task next() {
				last = currentIndex;
				return tasks[currentIndex++];
			}

			@Override
			public void remove() {
				if(currentIndex > 0 ){
					ArrayTaskList.this.remove(tasks[last]);
					currentIndex--;
				}
				else {
					throw new IllegalStateException();
				}
			}
		};
		return it;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ArrayTaskList))
			return false;
		ArrayTaskList tasks1 = (ArrayTaskList) o;
		return Arrays.equals(tasks, tasks1.tasks);
	}

	@Override
	public int hashCode() {
		return size()^tasks.length*16+1;
	}

	@Override
	public String toString() {
		return "ArrayTaskList{" +
			"tasks=" + Arrays.toString(tasks) +
			'}';
	}

	@Override
	public ArrayTaskList clone() throws CloneNotSupportedException {
		ArrayTaskList clone = new ArrayTaskList();
		for(int i = 0; i < size(); i++){
			clone.add(this.tasks[i]);
		};
		return clone;
	}


}
