package ua.edu.sumdu.j2se.pohorila.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Class LinkedTaskList.
 * @author Pohorila
 * @version 1.0
 *
 */

public class LinkedTaskList extends AbstractTaskList implements Cloneable{

	/**Inner class Node
 * */
	class Node {
	/** Information about next node.*/
		Node next;
	/**Information about task. */
		Task task;

		public Node(Task task) {
			this.task = task;
			this.next = null;
		}

		public Node(){
			this.task = null;
			this.task = null;
		}

	}

	/**
	 * @Node head - first node
	 * @Node tail - last node
	 * @int size - number of tasks in list
	 */
		Node head;
		Node tail;
		int size = 0;

	/**Method that add the task to list.
	 * @Task task - item for add
	 * */
		public void add(Task task) {
			Node a = new Node();
			if(task != null) {
				a.task = task;
				if (head == null) {
					head = a;
				} else {
					tail.next = a;
				}
				tail = a;
			}
			else{
				throw new NullPointerException("Null task");
			}
			this.size++;
		}
	/**Method that remove the task from task list.
	 * @Task task - item for deleting
	 *@return a value indicating whether the event was deleted or not*/
	public boolean remove (Task task) {
		boolean isRemove = false;
		Node current = head;
		Node previous = head;

		while (current.next != null || current == tail) {
			if (current.task == task) {
				if (size == 1) {
					head = null;
					tail = null;
				}
				else if (current == head) {
					head = head.next;
				}
				else if (current == tail) {
					tail = previous;
					tail.next = null;
				}
				else { previous.next = current.next; }
				size--;
				isRemove = true;
				break;
			}
			previous = current;
			current = previous.next;
		}
		return isRemove;
	}

	/**Method that returns the number of tasks.
	 * @return amount of tasks*/
		public int size() {
			return this.size;
		}

	/**Method that returns task from the index
	 * @param i index of necessary task
	 * @return needed task or null if index < 0*/
		public Task getTask(int i){
			if(i<=0 && i>size){
				return null;
			}
			Node p1 = head;
			int j = 0;
			while(j < i){
				j++;
				p1 = p1.next;
			}
			return p1.task;
	}

	@Override
	public	Iterator<Task> iterator() {

		Iterator<Task> it = new Iterator<Task>() {

			private int currentIndex = 0;
			private int last = -1;
			private Node next = head;

			@Override
			public boolean hasNext() {
				return currentIndex < size && getTask(currentIndex) != null;
			}

			@Override
			public Task next() {
				last = currentIndex;
				return getTask(currentIndex++);
			}

			@Override
			public void remove() {
				if(currentIndex > 0) {
					LinkedTaskList.this.remove(getTask(last));
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
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LinkedTaskList that = (LinkedTaskList) o;
		if (size != that.size) {
			return false;
		}
		for (Iterator<Task> it1 = iterator(), it2 = that.iterator(); it1.hasNext() && it2.hasNext();) {
			if (!it1.next().equals(it2.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		for (Iterator<Task> it = iterator(); it.hasNext(); hash += it.next().hashCode());
		return hash;
	}

	@Override
	public LinkedTaskList clone() throws CloneNotSupportedException {
		return (LinkedTaskList) super.clone();
	}

	public Stream<Task> getStream() {
		Task[] tasks = new Task[this.size()];
		for(int i = 0; i < size(); ++i) {
			tasks[i] = getTask(i);
		}
		return Arrays.stream(tasks);
	}

}
