/*
 * Write a generic method to count the number of elements in a collection 
 * that have a specific property (for example, odd integers, prime numbers,
 * palindromes).
 */ 

package es.javiergomez.tutorial.java.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class GenericMethodsEx {
	
	public static interface Property<T> {
		public boolean check(T t);
	}
	
	public static class IntegerPropertyImpl implements Property<Integer>{
		public boolean check(Integer t){	
			return (t % 2 != 0);
		}	
	}

	
	/*
	 * Generic method
	 * */
	
	
	public static <T> Integer countCondition(Collection<T> input, Property<T> property){
		
		Integer count = 0;
		
		for (T t : input){
			if (property.check (t)) count++;
		}
		return count;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		int result = 0;
		Collection<Integer> myInput = new ArrayList<>(Arrays.asList(5,6,7));
		Property<Integer> myProperty = new IntegerPropertyImpl();
		
		myInput.add(5);
		myInput.add(3);
		result = countCondition(myInput,myProperty);
		System.out.println(result);
	
	}
}
