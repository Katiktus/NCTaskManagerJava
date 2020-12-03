package ua.edu.sumdu.j2se.pohorila.tasks;

/**
 * Class LinkedTaskList.
 * @author Pohorila
 * @version 1.0
 *
 */

public class LinkedTaskList{

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
		public boolean remove(Task task) {
			boolean isRemove = false;
			if(task == null) {
				return false;
			}
			else if(head == tail) {
				head = null;
				tail = null;
				isRemove = true;
			}
			else if(head.task == task) {
				head = head.next;
				isRemove = true;
			}
			Node a = head;
			do{
				if(a.next.task == task){
					if(tail == a.next) {
						tail = a;
					}
					a.next = a.next.next;
					this.size--;
					return true;
				}
				a = a.next;
			}
			while(a.next != null);
			if(isRemove){
				this.size--;
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
	/**Method that returns list of incoming tasks.
	 * @param from start interval time
	 * @param to  end interval time
	 * @return list of incoming tasks*/
		public LinkedTaskList incoming(int from, int to){
			LinkedTaskList list = new LinkedTaskList();
			for (Node a = this.head; a!=null; a = a.next) {
				if (a.task.nextTimeAfter(from) > from && a.task.nextTimeAfter(from) <= to){
					list.add(a.task);
			}
			}
			return list;
		}
}

