package ethz.ch.pp.assignment2;

public class TaskARunnable implements Runnable{
	
	@Override
	public void run() {
		System.out.println("Hi there!");
		System.out.println("Greetings from thread " + Thread.currentThread().getName());
	}

}
