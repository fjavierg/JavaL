package es.javiergomez.concurrency;

public class JoyThreadReceiver implements Runnable{

	Joy joy;
	
	public JoyThreadReceiver(Joy joy) {
		super();
		this.joy = joy;
	}

	@Override
	public void run() {
		
		try{
			joy.guardedJoy();
		}catch(InterruptedException e) {}
		
		System.out.format("Notified RECEIVED: %s%n", Thread.currentThread().getName());
				
	}
}
