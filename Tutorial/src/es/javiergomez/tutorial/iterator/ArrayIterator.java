package es.javiergomez.tutorial.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArrayIterator {
	
	

	public ArrayIterator() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] array = new String [] {"a","b","c","a"};

		System.out.println(Arrays.toString(array));
		
		
		// Option 1
		System.out.println ("First option Foreach loop");
		for (int i = 0; i < array.length; i++) {
			System.out.println (array[i]);
		}

		// Option 2
		System.out.println ("Second option Enhanced For");
		for (String iter : array){
			System.out.println (iter);
		}
		
		// Option 3
		System.out.println ("Third option Collection List iterator");
		List<String> myList= new ArrayList<String>(Arrays.asList(array));
		Iterator<String> myIter = myList.iterator();
		while (myIter.hasNext()){
			System.out.println (myIter.next());
		}
		
		//Eliminate duplicates
		System.out.println ("Eliminate duplicates using Set interface");
		Set<String> mySet = new HashSet (Arrays.asList(array));
		Iterator<String> myIter2= mySet.iterator();
		while (myIter2.hasNext()){
			System.out.println (myIter2.next());
		}
		
		
		//equals and hashcode
				System.out.println ("equals : " + array[0].equals(array[1]));
				System.out.println ("hashcode . " + array[0].hashCode());
				System.out.println ("hashcode . " + array[1].hashCode());
				System.out.println ("hashcode . " + myIter.hashCode());
	}

}
