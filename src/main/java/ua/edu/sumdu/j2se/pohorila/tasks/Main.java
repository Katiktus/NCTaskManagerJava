package ua.edu.sumdu.j2se.pohorila.tasks;

import ua.edu.sumdu.j2se.pohorila.cli.*;
import ua.edu.sumdu.j2se.pohorila.notification.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		LoadManager load = new LoadManager();
		load.loader();
	}
}
