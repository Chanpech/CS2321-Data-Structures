import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ArrayProblemsTest {

	@Before
	public void setUp() throws Exception {
	}

	/*
	sortArray CASE 1:
	Input: [5,2,5,3,1]
	Expected Output: [5,2,3,1]
	 */
	@Test
	public void testDeleteDuplicatesArray1() {
		int[] nums= {5,2,5,3,1};
		int[] expected = {5,2,3,1};
		int[] actural = ArrayProblems.deleteDuplicates(nums);
		assertArrayEquals(expected, actural);
	}

	/*
	sortArray CASE 2:
	Input: [5,1,5,2,0,1,5,1]
	Expected Output: [5,1,2,0]
	 */
	@Test
	public void testDeleteDuplicatesArray2() {
		
		// TODO: Implement this test case by following the example of testSortArray1()
		fail("Not yet implemented"); // TODO
	}


	/*
	sortArray CASE 3: no duplicates
	Input: [1,2,3,4,5]
	Expected Output: [1,2,3,4,5]
	 */
	@Test
	public void testDeleteDuplicatesArray3() {
		
		// TODO: Implement this test case by following the example of testSortArray1()
		fail("Not yet implemented"); // TODO
	}

	

	/*
	sortArray CASE 4: all duplicates
	Input: [1,1,1,1,1,]
	Expected Output: [1]
	 */
	@Test
	public void testDeleteDuplicatesArray4() {
		
		// TODO: Implement this test case by following the example of testSortArray1()
		fail("Not yet implemented"); // TODO
	}

}
