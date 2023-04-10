import java.util.Arrays;

public class ArrayProblems {

	/*
	Given an array of integers, generate a new array with all duplicates removed except the first appearance of each number.
	

	Example 1:
	Input:  [5, 2, 5, 3, 1]
	output: [5, 2, 3, 1]

	
	Example 2:
	Input:  [5, 1, 5, 1, 2, 1, 0, 4, 0]
	output: [5, 1, 2, 0, 4]
	*/
	
	public static int[] deleteDuplicates(int[] nums) {
    	int[] temp;
    	temp = {5,2,3,1};
		//TODO:finish this method. 	
		return temp;
				
	}
	public static void main(String args[]) {
		int[] nums = {5,2,5,3,1};
		int[] actural = ArrayProblems.deleteDuplicates(nums);
		System.out.println("Output: " + Arrays.toString(actural));
    	
	}


    
}
