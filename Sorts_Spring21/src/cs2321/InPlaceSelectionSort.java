package cs2321;
/**
 * @author Chanpech Hoeng
 * @generic <E>
 */
public class InPlaceSelectionSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(E[] array) {
		/* TCJ:
		 * At worst it traverses through all of the value in the nested loops.
		 */
		/*
		 * K is an generic, but comparable type. 
		 * Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
		 */
		// TODO Auto-generated method stub
		for(int i = 0; i < array.length - 1; i++) {
			int minIndex  = i;
			for(int j = i + 1; j <= array.length - 1; j++) {
				if(array[j].compareTo(array[minIndex]) < 0) {
					minIndex = j;
				}
			}
			if(minIndex != i ) {
				swap(array, minIndex, i);
			}
		}
	}
	/**
	 * swap - swap the element at the two given index
	 * @param array, mid, i
	 */
	public void swap(E[] array, int mid, int i) {
		E temp = array[mid];
		array[mid] = array[i];
		array[i] = temp;
	}


}
