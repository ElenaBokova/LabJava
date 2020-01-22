package ru.spbstu.telematics.java;
import java.util.Vector;

public class App {

	 public static void main(String[] args)
	    {
	        System.out.println("Main thread started...");
	        
	        Vector<Integer> actions = new Vector<>();
	        int delta_time = 250;
	        
	        actions.add(-30 * delta_time);
	        actions.add(20 * delta_time);
	        actions.add(5 * delta_time);
	        actions.add(-25 * delta_time);
	        actions.add(3 * delta_time);
	        
	        Window window = new Window(actions, delta_time);
	        window.Start();
	        
	        System.out.println("Main thread finished...");
	    }
}
