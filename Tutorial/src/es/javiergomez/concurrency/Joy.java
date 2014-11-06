package es.javiergomez.concurrency;

public class Joy {

	boolean joy = false;
	
	public synchronized void guardedJoy()throws InterruptedException {
	    // This guard only loops once for each special event, which may not
	    // be the event we're waiting for.
	    while(!joy) {
	        try {
	            wait();
	        } catch (InterruptedException e) {throw e;}
	    }
	    System.out.println("Joy and efficiency have been achieved!");
	}
	
	public synchronized void notifyJoy() {
	    joy = true;
	    notify();
	}
	
	public synchronized void notifyAllJoy() {
	    joy = true;
	    notifyAll();
	}
}
