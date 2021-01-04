package ua.edu.sumdu.j2se.pohorila.tasks;

import com.google.gson.Gson;
import java.io.*;
import java.nio.Buffer;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;


public class TaskIO {

	public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
		try(DataOutputStream stream = new DataOutputStream(out)){
			stream.writeInt(tasks.size());
			for (Task task : tasks) {
				stream.writeInt(task.getTitle().length());
				stream.writeUTF(task.getTitle());
				stream.writeInt(task.isActive() ? 1 : 0);
				stream.writeInt(task.getRepeatInterval());
				if(task.isActive()){
					stream.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
					stream.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
				}
				else{
					stream.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));
				}
			}
		}
	}

	public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
		try(DataInputStream stream = new DataInputStream(in)) {
			int size = stream.readInt();
			for(int i = 0; i < size; i++){
				Task task;
				int length = stream.readInt();
				String title = stream.readUTF();
				Boolean active = (stream.readInt() == 1);
				int interval = stream.readInt();
				if(active){
					long startTime = stream.readLong();
					long endTime = stream.readLong();
					LocalDateTime start = Instant.ofEpochSecond(startTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
					LocalDateTime end = Instant.ofEpochSecond(endTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
					task = new Task(title, start, end, interval);
				}
				else {
					long timeRead = stream.readLong();
					LocalDateTime time = Instant.ofEpochSecond(timeRead).atZone(ZoneId.systemDefault()).toLocalDateTime();
					task = new Task(title, time);
				}
				task.setActive(active);
				tasks.add(task);
			}
		}
	}

	public static void writeBinary(AbstractTaskList tasks, File file) throws FileNotFoundException {
		try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {
			write(tasks, stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readBinary(AbstractTaskList tasks, File file){
		try(BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file))) {
			read(tasks, stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(AbstractTaskList tasks, Writer out)  throws IOException{ //?
		String json = new Gson().toJson(tasks);
		try (BufferedWriter writer = new BufferedWriter(out)) {
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void read(AbstractTaskList tasks, Reader in){ //?
		AbstractTaskList abstractTasks = new Gson().fromJson(in, tasks.getClass());
		for (Task task: abstractTasks) {
			tasks.add(task);
		}
	}

	public static void writeText(AbstractTaskList tasks, File file) throws IOException {
		String json = new Gson().toJson(tasks);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(json);
			writer.flush();
		}
	}

	public static void readText(AbstractTaskList tasks, File file) throws FileNotFoundException {
		try(BufferedReader input = new BufferedReader(new FileReader(file))) {
			read(tasks,input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
