package es.javiergomez.tutorial.algos.exercises;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/*
 * Josephus problem. In the Josephus problem from antiquity, N people are in dire straits and agree to the following strategy 
 * to reduce the population. They arrange themselves in a circle (at positions numbered from 0 to N???1) and proceed around 
 * the circle, eliminating every Mth person until only one person is left. Legend has it that Josephus figured out where to sit
 *  to avoid being eliminated. Write a Queue client Josephus.java that takes M and N from the command line and prints out the 
 *  order in which people are eliminated (and thus would show Josephus where to sit in the circle).
 *  
 *   % java Josephus 2 7
 *    1 3 5 0 4 2 6
 */


public class Josephus {
	
	private List<Integer> circle;
	
	public Josephus(List<Integer> list, int n) {
		
		circle = list;
		circle.clear();
		for (int i = 0; i < n; i++){			
			circle.add(i);
		}
	}
	
	public void doit(int m) {
		int i = 0;
		
		while (circle.size() > 0) {
			i = (i -1 + m) % circle.size();
			System.out.println(circle.get(i));
			circle.remove(i);		
		}		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		if (args.length < 2) {
			System.out.println("Invalid number of args %n Usage Josephus N M");
		} else {
			int m = Integer.valueOf(args[0]);
			int n = Integer.valueOf(args[1]);

// Josephus requires a class implementing List Interface
// Decide here what implementation of List is beter for you
			
			List<Integer> myCircle = new ArrayList<Integer>();
			Josephus myJos = new Josephus(myCircle,n);
			myJos.doit(m);

		}
	}
}
