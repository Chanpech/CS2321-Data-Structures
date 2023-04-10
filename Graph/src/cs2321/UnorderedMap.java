package cs2321;
import java.util.Iterator;

/**
 * @author ruihong-adm
 * @author Chanpech Hoeng
 * @param <E>
 */
import net.datastructures.Entry;
import net.datastructures.Map;

public class UnorderedMap<K,V> extends AbstractMap<K,V> {

	/* Use ArrayList or DoublyLinked list for the Underlying storage for the map of entries.
	 * TODO:  Uncomment one of these two lines;
	 * private ArrayList<mapEntry<K,V>> table; 
	 * private DoublyLinkedList<mapEntry<K,V>> table;
	 */

	private ArrayList<mapEntry<K,V>> table;

	public UnorderedMap() {
		// TODO Auto-generated constructor stub
		table = new ArrayList<>();
	}


	@Override
	public int size() {
		// Return the number of entries in map
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		// Return a boolean indicating whether map is empty
		return table.size() == 0;
	}

	private int findIndex(K key) {
		for(int i = 0; i < table.size(); i++) {
			if(table.get(i).getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}

	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	@Override
	public V get(K key) {
		/* use equals method to compare keys, do NOT use == */
		// Search for key within the map and if exist we want to the value of that entry. 
		// Otherwise return null

		int index = findIndex(key);
		if(index == -1) {
			return null;
		}
		return table.get(index).getValue();

	}
	
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	@Override
	public V put(K key, V value) {
		/* use equals method to compare keys, do NOT use == */
		// If the element within the map don't have the same key we want to add the entry and return null.
		// Otherwise replace and return the old value. 
		for(int i =0 ; i < table.size(); i++) {
			mapEntry<K, V> e = table.get(i);
			if(e.getKey().equals(key)){
				V oldV = e.getValue();
				e.setValue(value);
				return oldV;
			}
		}
		mapEntry<K,V> entry = new mapEntry<K,V>(key,value);
		table.addLast(entry);
		return null;
	}
	
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	@Override
	public V remove(K key) {
		/* use equals method to compare keys, do NOT use == */
		// Remove the key in the parameter. If it exist, returns the value.

		for(int i = 0; i < table.size(); i++) {
			mapEntry<K,V> entry = table.get(i);
			if(entry.getKey().equals(key)) {
				V oldValue = entry.getValue();
				table.remove(i);
				return oldValue;
			}
		}
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		//Hint: Java does not allow the casting from DoublyLinkedList<mapEntry<K,V>> to DoublyLinkedList<Entry<K,V>>
		//Therefore, you can create a new variable with the type DoublyLinkedList<Entry<K,V>>, then add all the data in table to it.
		Iterator<mapEntry<K, V>> e = table.iterator();
		ArrayList<Entry<K, V>> newV = new ArrayList<>();
		while(e.hasNext()) {
			newV.addLast(e.next());
		}
		return newV;
	}

}
