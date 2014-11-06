package es.javiergomez.tutorial.reflection;

public class Reflection {

	enum En { A, B };
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Class c = "foo".getClass();
		
		System.out.println(c.toString());

		
		Class d = En.class;
		System.out.println(En.A.getClass().toString());
		System.out.println(d.toString());
		System.out.println(d.getEnclosingClass().toString());
		System.out.println(d.getSuperclass().toString());
		
		Class e = Reflection.class;
		System.out.println(e.toString());
		System.out.println(e.getSuperclass().toString());

	}

}
