package es.javiergomez.concurrency;

public class JoyThreadNotifier implements Runnable {

	Joy joy;
	
	public JoyThreadNotifier(Joy joy) {
		super();
		this.joy = joy;
	}

	@Override
	public void run() {
		
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
		
		System.out.println("Notifying");
		joy.notifyAllJoy();
				
	}

}
