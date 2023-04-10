package cs2321;
/**
 * @author Chanpech Hoeng
 * @generic <E>
 */
public class QuickSort<E extends Comparable<E>> implements Sorter<E> {
	/**
	 * sort - Perform quick sort. -Feel free to create other methods. 
	 * @param array - Array to sort
	 */
	@TimeComplexity("O(n^2)")
	public void sort(E[] array) {
		/* TCJ:
		 * At worst all of the value in nested loops in partition will get executed 
		 * thus cost n^2
		 */
		/*
		 * K is an generic, but comparable type. 
		 * Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
		 */
		// TODO Auto-generated method stub
		quickSort(array, 0, array.length -1);
	}
	/**
	 * main sort call is the quickSort
	 * @param E[]a, int p, int q pass in array and ranges
	 * @return void
	 */
	public void quickSort(E[] a, int p, int q) {
		if(p < q) {
			//call partition method to divide the data
			int part = partition(a, p, q); 

			//call quickSort to sort the data part that is to the left of the pivot
			quickSort(a, p, part -1);

			//call quickSort to sort the data part that is to the right of the pivot
			quickSort(a, part + 1, q);

		}
	}
	/**
	 * partition split method sort method
	 * @param E[]a, int p , int r pass in our array and range
	 * @return void
	 */
	public int partition(E[] a, int p, int r) { 
		E pivot = a[r];
		int i = p;
		int j = r -1;
		while(i <= j) {
			while(i <= j && (a[i].compareTo(pivot) <= 0)){ //Compare value i <= pivot
				i++;
			}while(j >= i && (a[j].compareTo(pivot) >= 0)){//Compare value j >= pivot
				j--;
			}
			if( i < j ) {
				swap(a, i, j);
				i++;
				j--;
			}
		}
		//put pivot at the right spot by swapping r and i
		swap(a ,r,i);//Call helper
		return i;

	}
	/**
	 * swap passed in value
	 * @param E[]a, int p , int r pass in our array and range
	 * @return void
	 */
	public void swap(E[] a,int r , int i) {
		E temp = a[i];
		a[i] = a[r];
		a[r] = temp;
	}
}
