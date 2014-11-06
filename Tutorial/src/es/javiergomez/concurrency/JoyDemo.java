package es.javiergomez.concurrency;

public class JoyDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Joy joy = new Joy();
        (new Thread(new JoyThreadNotifier(joy))).start();
        (new Thread(new JoyThreadReceiver(joy))).start();
        (new Thread(new JoyThreadReceiver(joy))).start();
        (new Thread(new JoyThreadReceiver(joy))).start();
        System.out.println("Threads launched");

	}

}
