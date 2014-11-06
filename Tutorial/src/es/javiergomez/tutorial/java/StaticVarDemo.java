package es.javiergomez.tutorial.java;

public class StaticVarDemo {
	
    public static int x = 7; 
    public int y = 3; 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StaticVarDemo a = new StaticVarDemo();
		StaticVarDemo b = new StaticVarDemo();
		a.y = 5;
		b.y = 6;
		a.x = 1;
		b.x = 2;
		System.out.println("a.y = " + a.y);
		System.out.println("b.y = " + b.y);
		System.out.println("a.x = " + a.x);
		System.out.println("b.x = " + b.x);
		System.out.println("StaticVar.x = " + StaticVarDemo.x);

	}

}
