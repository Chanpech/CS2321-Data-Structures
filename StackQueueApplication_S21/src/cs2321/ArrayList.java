package cs2321;

import java.util.Iterator;

import net.datastructures.List;
/**
 * @author ruihong-adm
 * @author Chanpech Hoeng
 * @param <E>
 */
public class ArrayList<E> implements List<E> {

	//Instance variables
	public static int defaultCapacity = 16; //Initial capacity
	private E[] data; // Empty array data 
	public int size = 0; // Initial size

	//ArrayList default constructor
	public ArrayList() {
		this(defaultCapacity);
	}

	/**
	 * Constructor a new ArrayList instance
	 * @param cap capacity
	 */
	public ArrayList(int cap){
		data = (E[]) new Object[cap];
	}

	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Tests whether the list is empty.
	 * @return true if the list is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Returns (but does not remove) the element at index i.
	 * @param  i   the index of the element to return
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
	 */
	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex( i, size );
		return data[ i ];
	}
	/**
	 * Replaces the element at the specified index, and returns the element previously stored.
	 * @param  i   the index of the element to replace
	 * @param  e   the new element to be stored
	 * @return the previously stored element
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
	 */
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex( i,  size );
		E temp = data[ i ];
		data[i] = e;
		return temp;
	}
	/**
	 * Inserts the given element at the specified index of the list, shifting all
	 * subsequent elements in the list one position further to make room.
	 * @param  i   the index at which the new element should be stored
	 * @param  e   the new element to be stored
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size()
	 */
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex( i , size + 1);
		if(i > data.length) {
			throw new IndexOutOfBoundsException("Array is full.");
		}
		for(int r = size; r >= i; r--) {//Shifting elements over by one from the i position
			data[r + 1] = data[r];
		}
		data[i] = e;
		size++;

	}
	/**
	 * Removes and returns the element at the given index, shifting all subsequent
	 * elements in the list one position closer to the front.
	 * @param  i   the index of the element to be removed
	 * @return the element that had be stored at the given index
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size()
	 */
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		for(int r = i; r < size -1; r++) {
			data[r] = data[r+1];
		}
		data[size - 1] = null;
		size--;
		return data[i];
	}

	//Inner MyIterator class that implements iterator
	class MyIterator implements Iterator<E>{
		private int j = 0;
		private boolean removable = false;

		@Override
		public boolean hasNext() {
			return j < size;
		}

		@Override
		public E next() throws IndexOutOfBoundsException{
			if(j == size)
				throw new IndexOutOfBoundsException("No next element");
			removable = true;
			return data[j++];
		}
	}

	/**
	 * Returns an iterator of the elements stored in the list.
	 * @return iterator of the list's elements
	 */
	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	/**
	 * Method which add the given element as the first
	 * @param e the element to add
	 * @void
	 */
	public void addFirst(E e)  {
		checkCapacity(size+1);
		for(int r = size; r >= 0; r--) {//Shifting elements over by one from the i position
			data[r + 1] = data[r];
		}
		data[0] = e;
		size++;
	}

	/**
	 * Method which add the given element as the last
	 * @param e the element to add
	 * @void
	 */
	public void addLast(E e)  {
		checkCapacity(size);
		data[size] = e;
		size++;

	}
	/**
	 * Method which remove the first element
	 * @return E the removed element
	 */
	public E removeFirst() throws IndexOutOfBoundsException {
		E temp = data[0];
		for(int r = 0; r < size -1; r++) {
			data[r] = data[r+1];
		}
		data[size-1] = null;
		size--;
		return temp;

	}

	/**
	 * Method which remove the last element
	 * @return E the removed element
	 */
	public E removeLast() throws IndexOutOfBoundsException {
		E temp = data[size -1];
		data[size] = null;
		size--;
		return temp;
	}

	// Return the capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled. 
	public int capacity() {
		return defaultCapacity;
	}

	/**
	 * Capacity checker and updater if array exceed capacity
	 * @void
	 */
	private void checkCapacity(int i) {
		if(i >= defaultCapacity) {
			E[] newArray;
			defaultCapacity = defaultCapacity * 2;
			newArray = (E[])new Object[defaultCapacity];
			for(int r =0; r < size; r++) {
				newArray[r] = data[r];
			}
			data = newArray;
		}
	}

	/**
	 * Index checker for out of bounds purpose
	 * @param i index
	 * @param s size
	 */
	private void checkIndex(int i,int s) {
		checkCapacity(s);
		if(i < 0 || i >= s) {
			throw new IndexOutOfBoundsException("Illegal index");
		}
	}

}
