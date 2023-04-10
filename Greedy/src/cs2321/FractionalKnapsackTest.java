package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cs2321.HeapAPQ.PQEntry;
import net.datastructures.Entry;


/**
 *  FractionalKnapsackTest test same setup but with different limits
 * 
 * Course: CS2321 Section R02
 * Assignment: #4
 * @author:Chanpech Hoeng
 *
 */
public class FractionalKnapsackTest {
	
	FractionalKnapsack knapsack;
	int[][] items = new int[5][1];
	Entry<Integer,Integer> newEntry = new PQEntry<>(5,3,4);
	@Before
	public void setUp() throws Exception {
		knapsack = new FractionalKnapsack();
		items[0][0]= 4; 
		items[0][1]= 12; 
		items[1][0]= 8; 
		items[1][1]= 32; 
		items[2][0]= 2; 
		items[2][1]= 40; 
		items[3][0]= 6; 
		items[3][1]= 30; 
		items[4][0]= 1; 
		items[4][1]= 50; 
	}
/*
 * items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
 */
	@Test
	public void testMaximumValue() {
		double expected = 124;
		int weightlimit = 10;
		assertEquals("maximum value should be ", expected, knapsack.MaximumValue(items, weightlimit));
	}

	@Test
	public void testMaximumValue2() {
		int expected = 50;
		int weightlimit = 1;
		assertEquals("maximum value should be ", expected, knapsack.MaximumValue(items, weightlimit)); // TODO
	}
	

}
