package cs2321;
/**
 * @author Chanpech Hoeng
 * @generic <E>
 * Used an import since professor approve of it
 */
import java.util.Arrays;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {
	/**
	 * sort - Perform merge sort. -Feel free to create other methods. 
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n logn)")
	public void sort(E[] array) {
		/* TCJ:
		 * At worst it the all of the array value will be traverse and 
		 * because of the sort right and sort left it cost log n time. 
		 */
		/*
		 * K is an generic, but comparable type. 
		 * Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
		 * 
		 * If you need to create an array of E,  use the following line as E is Comparable
		 * E[]  newarray = (E[]) Comparable[];
		 */
		
		// TODO Auto-generated method stub
		int n = array.length;
		if(n < 2) {
			return;
		}
		int mid = n /2; 
		E[] left = Arrays.copyOfRange(array, 0, mid);
		E[] right = Arrays.copyOfRange(array, mid, n);
		//Sort A1
		sort(left);
		//Sort A2
		sort(right);
		//called merge to merge A1 and A2
		merge(left, left.length, right, right.length, array);
	}
	/**
	 * merge the given arrays together into the main one a
	 * @param E[]a, int n1 , E[] a2, int n2, E[]a pass in our arrays
	 * @return void
	 */
	public void merge(E[] a1, int n1, E[] a2, int n2, E[] a) {
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < n1 && j < n2) {
			if(a1[i].compareTo(a2[j]) <= 0) {
				a[k] = a1[i];
				i++;
			}else {
				a[k] = a2[j];
				j++;
			}
			k++;
		}
		while(i < n1) {//Update our array into one
			a[k] = a1[i];
			i++;
			k++;
		}
		while(j < n2) { //Update our array into one 
			a[k] = a2[j];
			j++;
			k++;
		}
	}
}

