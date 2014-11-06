package es.javiergomez.tutorial.algos.exercises;

import java.math.BigInteger;



//you can also use imports, for example:
//import java.math.*;
class Solution {
	
	final static int PRIME = 10000103;
	
public static int solution ( int N, int M ) {

	BigInteger X = BigInteger.valueOf(N % PRIME);
	BigInteger Y = BigInteger.valueOf(M % PRIME);
	
	X = X.modPow(Y,BigInteger.valueOf(PRIME));
	System.out.println(X);
	
	return fibo (X);
}

public static int fibo ( BigInteger N ) {
	
	int km2 = 0;
	int km1 = 1;
	int km = 0;
	
	if (N.compareTo(BigInteger.ZERO) == 0) {
		km = 0;
	} else if (N.compareTo(BigInteger.ONE) == 0){
		km = 1;
	} else {
			for (BigInteger bi = BigInteger.valueOf(2); bi.compareTo(N) < 1; bi = bi.add(BigInteger.valueOf(1))){
				   km = (km1 + km2) % PRIME;
				   km2 = km1;
				   km1 = km;
				}
	}
	return (int)km;
	
}

public static void main(String[] args) {

	
	if (args.length < 2) {
		System.out.println("Invalid number of args");
	} else {

		int n = Integer.valueOf(args[0]);
		int m = Integer.valueOf(args[1]);
		
		n = 1000000000;
		m = 1111111111;

		System.out.println(solution(n,m));

	}
}

}
