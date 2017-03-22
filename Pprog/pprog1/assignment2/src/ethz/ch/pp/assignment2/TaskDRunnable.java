package ethz.ch.pp.assignment2;

import ethz.ch.pp.assignment2.Main.ArraySplit;

public class TaskDRunnable implements Runnable{
	
	private int[] factors;
	private int[] values;
	private ArraySplit split;

	public TaskDRunnable(int[] values, int[] factors, ArraySplit split) {
		this.values = values;
		this.factors = factors;
		this.split = split;
	}
	
	@Override
	public void run() {
		int endIndex = split.startIndex + split.length;
		for (int i = split.startIndex; i < endIndex; i++) {
			factors[i] = Main.numPrimeFactors(values[i]);
		}		
	}

}
