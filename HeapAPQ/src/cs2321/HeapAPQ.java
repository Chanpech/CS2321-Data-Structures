package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Chanpech Hoeng
 */

public class HeapAPQ<K,V> implements AdaptablePriorityQueue<K,V> {

	public static class DefaultComparator<K> implements Comparator<K> {

		// This compare method simply calls the compareTo method of the argument. 
		// If the argument is not a Comparable object, and therefore there is no compareTo method,
		// it will throw ClassCastException. 
		public int compare(K a, K b) throws IllegalArgumentException {
			if (a instanceof Comparable ) {
				return ( (Comparable <K>) a ).compareTo(b);
			} else {
				throw new  IllegalArgumentException();
			}
		}
	}

	public static class PQEntry<K,V> implements Entry<K,V> {

		private K k; //Key
		private V v; //Value
		private int index; //Entry index

		//The constructor initializes key, value, and index.
		public PQEntry( K key, V value, int j ) {
			k = key;
			v = value;
			index = j;
		}

		//The method return the key
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return k;
		}

		//The method return the value
		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return v;
		}

		//The method return the index
		public int getIndex() {
			return index;
		}

		//The method set the key
		public void setKey(K key) {
			k = key;
		}

		//The method set the value
		public void setValue(V value) {
			v = value;
		}

		//The method set the index
		public void setIndex(int i) {
			index = i;
		}
	}

	protected ArrayList<Entry<K,V>> heap = new ArrayList<>(); //Creating heap with arrayList data structure
	protected DefaultComparator<K> comp; //Initialize default comparator


	/* If no comparator is provided, use the default comparator which simply calls compareTo() method , */
	public HeapAPQ() {
		//TODO: implement this method 
		this(new DefaultComparator<K>());

	}

	/* use specified comparator */
	public HeapAPQ(Comparator<K> c) {
		//TODO: implement this method 
		comp = (DefaultComparator<K>) c; //Set given comparator to current comparator
	}

	//Return the parent of current node
	public int parent(int j) {
		return (j-1)/2;
	}
	
	//Return the left child of current node
	public int left(int j) {
		return (2*j) +1;
	}
	
	//Return the right child of current node
	public int right(int j) {
		return (2*j) +2;
	}
	
	//Return if node has left child
	public boolean hasLeft(int j) {
		return left(j) < heap.size();
	}
	
	//Return if node has right child
	public boolean hasRight(int j) {
		return right(j) < heap.size();
	}

	/*
	 * Check key status
	 * @param key the key to compare
	 * @return boolean of the validity of the key
	 * @throw IllegalArgumentException if the key is not equal to itself
	 */
	public boolean checkKey(K key) throws IllegalArgumentException{
		try {
			return (comp.compare(key, key)==0);
		}catch(ClassCastException e) {
			throw new IllegalArgumentException("Invalid Key");
		}
	}

	/* 
	 * Return the array in ArrayList that is used to store entries  
	 * This method is purely for testing purpose of auto-grader
	 */
	public Object[] data() {
		//TODO: replace the line below to return the actual array in ArrayList
		//TODO: You may want to add a method in your arrayList implementation 
		//TODO: to allow the access to the array. 
		Object[] data = new Object[heap.size()];
		for(int i = 0 ; i < heap.size(); i ++) {
			data[i] = heap.get(i);
		}
		return  data;
	}

	/*
	 * Get heap size
	 * @return current size
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return heap.size();
	}

	/*
	 * Check for empty heap
	 * @return boolean of is size empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return heap.size() == 0;
	}

	/*
	 * Insert new entry into our adaptable heap
	 * @param key and value
	 * @return the newly added Entry<K,V>
	 * @throw IllegalArgumentException if the key is not valid
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(log n)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		checkKey(key); //Check validity of key
		Entry<K,V> newEntry = new PQEntry<>( key, value, heap.size() );
		heap.addLast(newEntry);//Add new entry to the end
		upHeap(heap.size()-1); //up heap newly added entry
		return newEntry;
	}

	/*
	 * Find the minimum entry
	 * @return Entry<K,V>
	 */
	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> min() {
		// TODO Auto-generated method stub
		if(heap.isEmpty()) {//Check if heap is empty to avoid null pointer
			return null;
		}
		return heap.get(0);
	}

	/*
	 * Remove the minimum which is at the root
	 * @return the removed Entry<K,V>
	 */
	@TimeComplexity("O(log n)")
	@Override
	public Entry<K, V> removeMin() {
		/* TCJ:
		 * Will be log n because it is calling the downHeap method which cost O(log n),
		 * thus make this log of n as well.
		 */
		swap(0 , heap.size()-1);//Swap the root and the last node
		Entry<K,V> minRemoved = heap.removeLast();
		downHeap(0);
		return minRemoved;
	}

	/*
	 * Swap the entries at the given value
	 * @param i and j of int type
	 */
	public void swap(int i, int j) {
		Entry<K,V> temp = heap.get(i);//Temporary entry, hold the current root
		heap.set(i,heap.get(j));
		heap.set(j,temp);
		((PQEntry<K,V>)heap.get(i)).setIndex(i);//swap the i entry's index
		((PQEntry<K,V>)heap.get(j)).setIndex(j);//swap the j entry's index
	}

	/*
	 * Down heap our heap from the given index
	 * @param i of integer
	 */
	@TimeComplexity("O(log n)")
	public void downHeap (int i) {
		/* TCJ:
		 * Down heap swaps at most h time, where h is the height of the heap and is order log n
		 */
		if(hasLeft(i) == false)//Base case have no left child
			return;
		//Find smallest between i, right and left child.
		int smallest = i;
		int left = left(i);
		int right = right(i);
		if(comp.compare(heap.get(smallest).getKey(), heap.get(left).getKey()) > 0) {
			smallest = left;
		}
		if(hasRight(i) && (comp.compare(heap.get(smallest).getKey(), heap.get(right).getKey()) > 0)) {
			smallest = right; // If smallest is bigger than the right we reassign our current smallest.
		}
		//Check if s is i;
		if(smallest != i) {
			swap(smallest, i);
			downHeap(smallest);
		}
	}
	
	/*
	 * Up heap the data start from given index up.
	 * @param i integer
	 */
	@TimeComplexity("O(log n)")
	public void upHeap (int i) {
		/* TCJ:
		 * Will at most move from the bottom level to root h times and it order log n 
		 */
		if(i == 0) //Base case: i got not parent
			return;
		int parent = parent(i);//Check parent key
		if(comp.compare(heap.get(i).getKey(), heap.get(parent).getKey()) < 0) {
			//swap the entry data i and parent
			swap( i, parent );
			upHeap (parent );//Recursive call.
		}
	}

	/*
	 * Check our entry to make sure it follows location
	 * @param entry of Entry<K,V> class
	 * @return the valid Entry<K,V>
	 * @throw IllegalArgumentException if entry is invalid
	 */
	public PQEntry<K,V> validate(Entry<K,V> entry)throws IllegalArgumentException{
		if(!(entry instanceof PQEntry))
			throw new IllegalArgumentException("Invalid entry");
		PQEntry<K,V> locator = (PQEntry<K, V>) entry;
		int j = locator.getIndex();
		if(j >= heap.size() || heap.get(j) != locator) {
			throw new IllegalArgumentException("Invalid Entry");
		}
		return locator;
	}
	
	/*
	 * Restore the heap properties by moving at index j upward or downward
	 * @param j of integer type
	 */
	public void bubble(int j ) {
		if(j > 0 && comp.compare(heap.get(j).getKey(),heap.get(parent(j)).getKey())< 0)
			upHeap(j);
		else
			downHeap(j);
	}

	/*
	 * Remove the given entry from heap
	 * @param entry of Entry<K,V>
	 * @throw IllegalArgumentException if entry is not valid
	 */
	@TimeComplexity("O(log n)")
	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		/* TCJ:
		 * At worst it called down either heap and up heap through bubble which cost O(log n)
		 * and use location awareness design as well.
		 */
		PQEntry<K,V> locator = validate(entry);
		int j = locator.getIndex();
		if(j == heap.size() -1) {
			heap.remove(j);
		}else {
			swap(j,heap.size()-1);
			heap.remove(heap.size()-1);
			bubble(j);
		}

	}

	/*
	 * Replace the entry with the new given key.
	 * Unable to get it working
	 * @param entry and key of Entry<K,V> and K
	 * @throw IllegalArgumentException if the entry is not valid
	 */
	@TimeComplexity("O(log n)")
	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		/* TCJ: 
		 * At worst it called down either heap and up heap through bubble which cost O(log n)
		 * and use location awareness design as well.
		 */
		PQEntry<K,V> locator = validate(entry);
		checkKey(key);
		locator.setKey(key);
		bubble(locator.getIndex());
	}

	/*
	 * Replace value of an entry
	 * @param entry and value 
	 * @throw IllegalArgumentException if entry is not valid
	 */
	@TimeComplexity("O(1)")
	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		PQEntry<K,V> locator = validate(entry);
		locator.setValue(value);
	}

}
