package ethz.ch.pp.assignment2;

public class TaskBRunnable implements Runnable{
	
	private int[] result;
	private int[] values;
	
	public TaskBRunnable(int[] values) {
		this.values = values;
	}
	
	@Override
	public void run() {
		result = Main.computePrimeFactors(values);
	}
	
	public int[] getResult(){
		return result;
	}

}
