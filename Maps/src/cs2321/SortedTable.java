package cs2321;

import net.datastructures.*;

/**
 * SortedTable implemented with arraylist
 * @author Chanpech Hoeng
 * @param <E>
 */
public class SortedTable<K extends Comparable<K>, V> extends AbstractMap<K,V>  {
	
	/* 
	 * Use Sorted ArrayList for the Underlying storage for the map of entries.
	 * TODO: Uncomment this line;
	 * 
	 */
	
	private ArrayList<mapEntry<K,V>> table; 
	
	public SortedTable(){
		//TO DO: finish the constructor. 
		table = new ArrayList<mapEntry<K,V>>();
	}
	

	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(log n)")
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		// Hint: 1. Use binary search 
		//       2. When you compare two keys, use compareTo method
		int j = binarySearch(key);
		if(j == table.size() || key.compareTo(table.get(j).getKey())!= 0) {
			return null;
		}
		return table.get(j).getValue();
	}
	
	private int binarySearch(K key) {
		return binarySearch(key, 0, table.size()-1);
	}
	private int binarySearch(K key, int low, int high) {
		if(high < low)
			return high + 1;
		int mid = (low + high) /2;
		int comp = key.compareTo(table.get(mid).getKey());
		if(comp == 0) {
			return mid;
		}else if(comp < 0) {
			return binarySearch(key, low, mid -1);
		}else {
			return binarySearch(key, mid + 1, high);
		}
	}

	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(log n)")
	@Override
	public V put(K key, V value) {
		/* TCJ:
		 * We're using binary search.
		 */
		int j = binarySearch(key);
		if(j < size() && key.compareTo(table.get(j).getKey())== 0) {//If key already exist
			V oldValue = table.get(j).getValue();
			table.get(j).setValue(value); //we want to update value
			return oldValue;
		}
		table.add(j, new mapEntry<K,V>(key,value)); //Otherwise add in new entry
		return null;
	}

	@TimeComplexity("O(n log n)")
	@TimeComplexityExpected("O(log n)")
	@Override
	public V remove(K key) {
		/* TCJ:
		 * We're using binary search.
		 */
		int j = binarySearch(key);
		if(j == size() || key.compareTo(table.get(j).getKey())!= 0) 
			return null;
		V oldValue =table.get(j).getValue();
		table.remove(j);
		return oldValue;
		
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		// Hint: same way as you did in UnorderedMap.
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return table.size() == 0;
	}


}
