package cs2321;
/**
 * @author ruihong-adm
 * @author Chanpech Hoeng
 * @param <E>
 */
import net.datastructures.*;

public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {

	/* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	 * 
	 */
	private UnorderedMap<K,V>[]  table;
	int 	size;  // number of mappings(entries) 
	int 	capacity; // The size of the hash table. 
	int     DefaultCapacity = 17; //The default hash table size

	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor= 0.75;  

	/**
	 * Constructor that takes a hash size
	 * @param hashtable size: the number of buckets to initialize 
	 */
	public HashMap(int hashtablesize) {
		// TODO Add necessary initialization
		capacity = hashtablesize;
		table = (UnorderedMap<K,V>[]) new UnorderedMap[capacity];
	}

	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
		// TODO Add necessary initialization
		capacity = DefaultCapacity;
		table = (UnorderedMap<K,V>[]) new UnorderedMap[capacity];

	}

	/* This method should be called by map an integer to the index range of the hash table 
	 */
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

	/*
	 * The purpose of this method is for testing if the table was doubled when rehashing is needed. 
	 * Return the the size of the hash table. 
	 * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
	 */
	public int tableSize() {
		return table.length;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return bucketGet(hashValue(key), key);
	}
	private V bucketGet(int h, K key) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null) 
			return null;
		return bucket.get(key);
	}
	
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		V answer = bucketput(hashValue(key), key, value);
		double check = (double)size / capacity; 
		if(check >= loadfactor ) {
			resize(2 * capacity);
		}
		return answer;
	}

	private V bucketput(int h, K key, V value) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null) {
			bucket = table[h] = new UnorderedMap<>();
		}
		int oldSize = bucket.size();
		V answer = bucket.put(key, value);
		size += (bucket.size() - oldSize);
		return answer;
	}
	private void resize(int newCap) {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(size);
		for(Entry<K,V> e: entrySet())
			buffer.addLast(e);
		capacity = newCap;
		creatTable();
		size = 0;
		for(Entry<K,V> e: buffer)
			put(e.getKey(), e.getValue());
	}

	private void creatTable() {
		table = (UnorderedMap<K,V>[]) new UnorderedMap[capacity];
	}

	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return bucketRemove(hashValue(key),key);
	}
	private V bucketRemove(int h, K key) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null)
			return null;
		int oldSize = bucket.size();
		V answer = bucket.remove(key);
		size -= (oldSize - bucket.size());
		return answer;
	}

	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		// Hint: call entrySet of each bucket map. 
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				for(Entry<K,V> entry: table[i].entrySet()) {
					buffer.addFirst(entry);
				}
			}
		}
		return buffer;
	}
}
