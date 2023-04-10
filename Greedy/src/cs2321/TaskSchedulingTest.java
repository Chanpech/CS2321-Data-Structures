package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *  TaskSchedulingTest test with large to small data
 * 
 * Course: CS2321 Section R02
 * Assignment: #4
 * @author:Chanpech Hoeng
 *
 */
public class TaskSchedulingTest {
	private TaskScheduling taskSch;
	
	@Before
	public void setUp() throws Exception {
		taskSch = new TaskScheduling();
	}

	
	@Test
	public void testNumOfMachines() {
		int[][] tasks = new int[7][2];
		tasks[0][0] = 3;
		tasks[0][1] = 7;
		tasks[1][0] = 1;
		tasks[1][1] = 4;
		tasks[2][0] = 1;
		tasks[2][1] = 3;
		tasks[3][0] = 2;
		tasks[3][1] = 5;
		tasks[4][0] = 4;
		tasks[4][1] = 7;
		tasks[5][0] = 6;
		tasks[5][1] = 9;
		tasks[6][0] = 7;
		tasks[6][1] = 8;
		int expected = 3;
		assertEquals("number of machines should be ", expected, taskSch.NumOfMachines(tasks) );
	}

	@Test
	public void testNumOfMachines2() {
		int[][] tasks = new int[2][2];
		tasks[0][0] = 1;
		tasks[0][1] = 4;
		tasks[1][0] = 1;
		tasks[1][1] = 3;
		int expected = 2;
		assertEquals("number of machines should be ", expected, taskSch.NumOfMachines(tasks) );
	}

	@Test
	public void testNumOfMachines3() {
		int[][] tasks = new int[1][2];
		tasks[0][0] = 1;
		tasks[0][1] = 4;
		int expected = 1;
		assertEquals("number of machines should be ", expected, taskSch.NumOfMachines(tasks) );
	}

}
