package ua.edu.sumdu.j2se.pohorila.tasks;

class Node {
	Node next;
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

public class LinkedTaskList{

		Node head;
		Node tail;
		int size = 0;

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
			/*else if(tail.task == task){
				tail = null;
			}*/
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

		public int size() {
			return this.size;
		}

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

