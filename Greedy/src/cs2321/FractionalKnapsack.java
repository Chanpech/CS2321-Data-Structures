package cs2321;

import net.datastructures.Entry;

/**
 * FractionalKnapsack take in input of two dimensional array 
 * and a limit to find the maximum value.  
 * However I was not able to solve this, thus it will likely fail.
 * Course: CS2321 Section R02
 * Assignment: #4
 * @author:Chanpech Hoeng
 *
 */
public class FractionalKnapsack {

	private static HeapAPQ<Integer, Integer> pq; //organized items 
	private static double maxValue;  //track max
	private static Object[] data; //convert PQ to data as helper variable

	public FractionalKnapsack() {
		pq = new HeapAPQ<Integer, Integer>();//Initialize our PQ
		maxValue = 0; //Initialize our maxvalue
	}
	/**
	 * Goal: Choose items with maximum total benefit but with weight at most W.
	 *       You are allowed to take fractional amounts from items.
	 *      
	 *  But was not able to accomplish
	 *       
	 * @param items items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
	 * @param knapsackWeight
	 * @return The maximum total benefit. Please use double type operation. For example 5/2 = 2.5
	 * 		 
	 */
	public static double MaximumValue(int[][] items, int knapsackWeight) {
		//TODO: Don't forget to modify the return value
		for(int i = 0; i < items.length; i++) {
			int weight = items[i][0];
			int benefit = items[i][1];
			int value = benefit/weight;
			//System.out.println("Value: " + value + " index: " + i);
			pq.insert(value, i);
		}
		int currentWeight = 0;
		while(currentWeight < knapsackWeight) {
			data = pq.data();
			int biggestIndex= getBiggest(data);
			pq.remove(((Entry<Integer,Integer>)data[biggestIndex]));
			data = pq.data();
			currentWeight += items[biggestIndex][0];
			//System.out.println("current Wegiht " + currentWeight);
		}
		/*
			for(int i = 0; i < data.length; i++) {
				System.out.println(((Entry<Integer,Integer>)data[i]).getKey());
			}
		 */
		return maxValue;
	}

	/**
	 * Helper method, which return the index of the entry with the bigger key
	 * 
	 *       
	 * @param data our data of PQ
	 * @return the index of the entry with the bigger key.
	 */
	public static int getBiggest(Object[] data) {
		int biggest = 0;
		int index = 0;
		for(int i = 0; i < data.length; i++) {
			//System.out.println("Entry key: " + ((Entry<Integer,Integer>)data[i]).getKey());
			if(biggest < ((Entry<Integer,Integer>)data[i]).getKey()) {
				biggest = ((Entry<Integer,Integer>)data[i]).getKey();//Hold the 
				index = ((Entry<Integer,Integer>)data[i]).getValue();//Hold the index
				//System.out.println("Biggest " + biggest + " Key:  " +((Entry<Integer,Integer>)data[i]).getKey());
			}
		}
		return index;
	}

}
