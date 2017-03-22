package ethz.ch.pp.assignment2;

import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import ethz.ch.pp.assignment2.Main.ArraySplit;

public class Main {

	public static void main(String[] args) {

		taskA();
		
		// initialize input data
		int[] input1 = generateRandomInput(1000);
		int[] input2 = generateRandomInput(10000);
		int[] input3 = generateRandomInput(100000);
		int[] input4 = generateRandomInput(1000000);
		
		// Sequential version
		taskB(input1);
		taskB(input2);
		taskB(input3);
		taskB(input4);

		long threadOverhead = taskC();
		System.out.format("Thread overhead on current system is: %d nano-seconds\n", threadOverhead);		
		
		// Parallel version
		int[] threadNumbers = {1,2,4,8,16,32,64,128};
		for(int i : threadNumbers){
			taskE(input4, i);
		}
		taskE(input1, 1);
		taskE(input2, 4);
		taskE(input3, 4);
		taskE(input4, 4);
		
	}
	
	private final static Random rnd = new Random(42);

	public static int[] generateRandomInput() {
		return generateRandomInput(rnd.nextInt(10000) + 1);
	}
	
	public static int[] generateRandomInput(int length) {	
		int[] values = new int[length];		
		for (int i = 0; i < values.length; i++) {
			values[i] = rnd.nextInt(99999) + 1;				
		}		
		return values;
	}
	
	public static int[] computePrimeFactors(int[] values) {		
		int[] factors = new int[values.length];	
		for (int i = 0; i < values.length; i++) {
			factors[i] = numPrimeFactors(values[i]);
		}		
		return factors;
	}
	
	public static int numPrimeFactors(int number) {
		int primeFactors = 0;
		int n = number;		
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				primeFactors++;
				n /= i;
			}
		}
		return primeFactors;
	}
	
	public static class ArraySplit {
		public final int startIndex;
		public final int length;
		
		ArraySplit(int startIndex, int length) {
			this.startIndex = startIndex;
			this.length = length;
		}
	}

	// TaskD
	public static ArraySplit[] PartitionData(int totalLength, int numPartitions) {
		ArraySplit[] splits = new ArraySplit[numPartitions];
		int start = 0; 
		for(int i = 0; i < numPartitions; i++){
			int partitionLength = totalLength / (numPartitions - i);
			splits[i] = new ArraySplit(start, partitionLength);
			start += partitionLength; 
			totalLength -= partitionLength;
		}

		return splits;
	}
	
	public static void taskA() {
		Thread t1 = new Thread(new TaskARunnable());
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {}
	}
	
	public static int[] taskB(final int[] values) {
		long startTime, endTime;

		// Create a single thread and measure the execution
		TaskBRunnable task = new TaskBRunnable(values);
		Thread t1 = new Thread(task);
		startTime = System.nanoTime();
		t1.start();
		try {
			t1.join();
			endTime = System.nanoTime();
			System.out.println("Time with single thread: " + (endTime - startTime) / 1.0e6);
		} catch (InterruptedException e) {}

		// Measure the execution on the main thread
		startTime = System.nanoTime();
		computePrimeFactors(values);
		endTime = System.nanoTime();
		System.out.println("Time with main thread: " + (endTime - startTime) / 1.0e6);

		return task.getResult();
	}
	
	// Returns overhead of creating thread in nano-seconds
	public static long taskC() {		
		long startTime, endTime;

		startTime = System.nanoTime();
		// --
		Thread t1 = new Thread();
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
		}
		// -- 
		endTime = System.nanoTime();

		return endTime - startTime;
	}
	
	public static int[] taskE(final int[] values, final int numThreads) {
		long startTime, endTime;

		ArraySplit[] splits = PartitionData(values.length, numThreads);

		int[] factors = new int[values.length];	

		LinkedList<Thread> threads = new LinkedList<Thread>();
		for(ArraySplit split : splits){
			threads.add(new Thread(new TaskDRunnable(values, factors, split)));
		}
		
		startTime = System.nanoTime();
		for(Thread t : threads){
			t.start();
		}
		for(Thread t : threads){
			try {
				t.join();
			} catch (InterruptedException e) {
			}
		}
		endTime = System.nanoTime();

		System.out.println("Time with " + numThreads + " threads: " + (endTime - startTime) / 1.0e6);

		return factors;
	}


}
