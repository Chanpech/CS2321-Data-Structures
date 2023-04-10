package cs2321;
/**
 * @author Chanpech Hoeng
 * @generic <E>
 * Unable to get it working down to problem with down heap
 */
import net.datastructures.Entry;

public class HeapPQSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an PrioiryQueue Sort using the heap implementation of PQ.
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n log n)")
	public void sort(E[] array) {
		/* TCJ:
		 * From class we learned that at worse it will run n log n time. 
		 * But since I am unable to figure this one out I am not sure.
		 */
		/*
		 * K is an generic, but comparable type. 
		 * Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
		 */
		//TODO: complete this sort algorithm. 

		maximumHeap(array);
	}
	/**
	 * want to maximize our heap through down heap
	 * @param a - an array
	 */
	public void maximumHeap(E[] a) {
		//make the input array to be a maximumHeap

		HeapAPQ<E, E> heap = new HeapAPQ<E,E>();
		for(int i = 0; i <= a.length -1; i++) {
			heap.insert(a[i], a[i]);
		}
		//do a down heap bottonup construction
		for(int i = a.length -1; i >= 0; i--) {
			heap.downHeap(i);
		}
		heap.downHeap(a.length-1);
		Object[] data = heap.data();
		/*
		for(int i = 0; i <= data.length -1; i++) {

			System.out.print(((Entry<E,E>)data[i]).getKey());
		}
		*/

	}
	//remove the maximum and put at the end of array
	public void update(E[] a, int n) {
		//removeMin (which remove the root) and put at the end of the array through swap

		//Shrink heap by 1

		//down heap
	}
	public void swap() {

	}

}
