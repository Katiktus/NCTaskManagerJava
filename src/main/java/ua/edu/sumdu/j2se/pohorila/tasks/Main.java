package ua.edu.sumdu.j2se.pohorila.tasks;

public class Main {
	public static void main(String[] args) {
		Task test = new Task("Course", 10);
		System.out.println(test.getTitle());
		test.setActive(true);
		test.setTitle("Course0");
		System.out.println(test.getEndTime());
		System.out.println(test.getRepeatInterval());
		Task test2 = new Task("Course1", 5, 9, 24);
		test2.setTime(25);
		test2.setTime(10, 20, 3);
		System.out.println(test2.getStartTime());
		System.out.println(test2.nextTimeAfter(59));
	}

}
