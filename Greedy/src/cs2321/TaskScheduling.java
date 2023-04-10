package cs2321;

import cs2321.HeapAPQ.PQEntry;
import net.datastructures.Entry;

/**
 * @author:Chanpech Hoeng
 *
 */
public class TaskScheduling {
	private static HeapAPQ<Integer, Integer> pq; // The task PQ
	private static HeapAPQ<Integer, Integer> machinePq; //The machine PQ
	private static int nMachine; //number of operating machine
	/*
	 * TaskScheduling Constructor
	 */
	public TaskScheduling() {
		pq = new HeapAPQ<Integer, Integer>();//Initialized PQ
		machinePq = new HeapAPQ<Integer, Integer>();//Initialized machinePQ
		nMachine = 0;//Initialized nMachine which is keep track of operating machine
	}
	
	/**
	 * Goal: Perform all the tasks using a minimum number of machines. 
	 * 
	 *       
	 * @param tasks tasks[i][0] is start time for task i
	 *              tasks[i][1] is end time for task i
	 * @return The minimum number or machines
	 */
   public static int NumOfMachines(int[][] tasks) {
	   for(int i = 0; i < tasks.length; i++) {
		   int start = tasks[i][0];
		   int end = tasks[i][1];
		   pq.insert(start, end);
	   }
	   int size = pq.size();
	   for(int i = 0; i < size; i++) {
		   Entry<Integer,Integer> mini = pq.removeMin();
		   checkMachine(mini);
	   }
	   
	  return nMachine;
   }
   /**
	 * Helper method, which check our current machine for the next available time
	 * 
	 *       
	 * @param node which hold the current minimum/earliest job in task PQ
	 * @return The minimum number or machines
	 */
   public static void checkMachine(Entry<Integer,Integer> node) {
	   if(machinePq.isEmpty()) {
		   //If there is no machine run then open one
		   nMachine++;
		   machinePq.insert(node.getValue(), nMachine);
	   }else if(machinePq.min().getKey() > node.getKey()) { 
		   //If the min available time in the machine is greater than the starting, open new machine
		   nMachine++;
		   machinePq.insert(node.getValue(), nMachine);
	   }else if(machinePq.min().getKey() <= node.getKey()) {
		   //Replace key if we have next available machine
		   machinePq.replaceKey(machinePq.min(), node.getValue());
	   }
   }
}
