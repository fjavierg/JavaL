package es.javiergomez.tutorial.algos.exercises;

import java.util.ArrayList;
import java.util.List;

//you can also use imports, for example:
//import java.math.*;
class CopyOfSolution {
public static int solution ( String S,String T,int K ) {

	int res;
	String U="";
		
	//
	res = parSolution (S,K);
	
	for (int j = S.length();j < T.length();j++){
		U = Long.toBinaryString((long) Math.pow(2, j));
		System.out.println("primera parte" + res);
		res = res + parSolution (U,K);
	}
	res = res - parSolution(T,K);
	
	return res;
}

public static int parSolution ( String U,int K ) {
	
	String kString = "";
	String sRight;
	int pos;
	int right;
	long lRight;
	// Generate string of k 0s	
	for (int i = 0; i < K; i++){
		   kString = kString + "0";
		}
	
	//search first occurrence of kString
	pos = U.indexOf(kString);
	
	
	if (pos>0) {
	//Number of binaries after pattern
	right = U.length()-pos-K;
	
	//Substring right
	sRight = U.substring(pos+K);
	lRight = (long) Math.pow(2, right);
	lRight = lRight - Long.parseLong(sRight, 2);
	
	
	return (int) lRight;}
	else {
		return 0;}
	
}

public static void main(String[] args) {

	
	if (args.length < 3) {
		System.out.println("Invalid number of args %n Usage Josephus N M");
	} else {
		String a = String.valueOf(args[0]);
		String b = String.valueOf(args[1]);
		int k = Integer.valueOf(args[2]);

		System.out.println(solution(a,b,k));

	}
}

}
