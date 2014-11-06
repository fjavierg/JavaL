package es.javiergomez.tutorial.java;

public class Adder {

	/** Javadoc test */
	public static void main (String[] args){
		Integer addition = 0;
		if (args.length < 2) {
			System.out.println("Invalid number of args %n Usage Adder Num1 Num2 Num3 ...");
		} else {
			for (int i = 0; i < args.length; i++){
				System.out.println (args[i]);
				addition += Integer.decode(args[i]);
			}
			System.out.println("Adder result :" + addition);
		}
	}

}
