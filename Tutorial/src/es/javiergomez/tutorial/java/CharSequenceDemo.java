package es.javiergomez.tutorial.java;

public class CharSequenceDemo implements CharSequence{

	/**
	 * @param args
	 */
	
	private char[] myCharSeq;
	
	public CharSequenceDemo (char[] arg0) {
		myCharSeq=  arg0;
	}
	
	@Override
	public char charAt(int arg0) {
		return myCharSeq[arg0];
	}

	@Override
	public int length() {
		return myCharSeq.length;
	}

	@Override
	public CharSequence subSequence(int arg0, int arg1) {
		return null;
	}

	public static void main(String[] args) {
		
		char[] arrTest = {'a','b','c','d'};
		char[] arrTest2 = new char[4];
		
		arrTest2[1]='b';
		
		CharSequenceDemo myCharSeq = new CharSequenceDemo(arrTest);
		 System.out.println (myCharSeq.length());
		 System.out.println (myCharSeq.charAt(2));

	
	}

}
