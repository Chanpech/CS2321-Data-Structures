package cs2321;
/**
 * @author Chanpech Hoeng
 * @generic <E>
 */
public class InPlaceInsertionSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(E[] array) {
		/* TCJ:
		 * At worst it called down either heap and up heap through bubble which cost O(log n)
		 * and use location awareness design as well.
		 */
		/*
		 * K is an generic, but comparable type. 
		 * Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
		 */
		for(int i = 1; i <= array.length -1; i++) {//Traverse n time
			E current = array[i];
			int j = i -1;
			while( j >= 0 && (array[j].compareTo(current) > 0) ) {//Compare the current value to the past sorted value 
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = current; //Traverse through previous value cause n time
		}
	}

}
