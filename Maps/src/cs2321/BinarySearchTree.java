package cs2321;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.SortedMap;

/**
 * Implementation of BinarySearchTree data structure which uses
 * our own LinkedBinaryTree implementation.
 * @author ruihong-adm
 * @author Chanpech Hoeng
 * @param <E>
 */
public class BinarySearchTree<K extends Comparable<K>,V> extends AbstractMap<K,V>  {

	/* all the data will be stored in the tree*/
	LinkedBinaryTree<Entry<K,V>> tree; 
	int size;  //the number of entries (mappings)

	/* 
	 * default constructor
	 */
	public BinarySearchTree() {
		// TODO Add necessary initialization
		tree = new LinkedBinaryTree<>();
		tree.addRoot(null);
	}

	/* 
	 * Return the tree. The purpose of this method is purely for testing. 
	 * You don't need to make any change. Just make sure to use variable tree to store your entries. 
	 */
	public LinkedBinaryTree<Entry<K,V>> getTree() {
		return tree;
	}
	
	/*
	 * Helper methods Expand the external node at the given position
	 */
	private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry) {
		tree.setElement(p, entry);
		tree.addLeft(p, null);
		tree.addRight(p, null);
		size++;
	}
	
	/*
	 * Helper methods: search and compare node to the given key
	 */
	@TimeComplexity("O(h)")
	private Position<Entry<K,V>> search(Position<Entry<K,V>> p, K key) {
		if(tree.isExternal(p)) {
			return p;
		}
		K compKey = p.getElement().getKey();
		if(key.compareTo(compKey) == 0) {
			return p;
		}else if(key.compareTo(compKey) < 0) {
			return search(tree.left(p), key);
		}else {
			return search(tree.right(p), key);
		}

	}
	
	@Override
	public int size(){
		// TODO Auto-generated method stub
		return size;
	}
	

	@TimeComplexity("O(h)")
	@TimeComplexityExpected("O(h)")
	@Override
	public V get(K key) {
		/* TCJ:
		 * At worst the tree will traverse to the largest the node with the largest height.
		 * Using the search helper method. 
		 */
		Position<Entry<K,V>> v = search(tree.root(), key);
		if(tree.isExternal(v)) {
			return null;
		}else {
			return v.getElement().getValue();
		}
	}
	

	@TimeComplexity("O(h)")
	@TimeComplexityExpected("O(h)")
	@Override
	/**
	 * put method
	 * @param K , V given key and value
	 */
	public V put(K key, V value) {
		/* TCJ:
		 * At worse we search through the node with the largest height. 
		 * Using search and expandExternal helper
		 */
		Entry<K,V> newEntry = new mapEntry<>(key,value);
		Position<Entry<K,V>> w = search(tree.root(), key);
		if(tree.isExternal(w)) {
			expandExternal(w, newEntry);
			return null;
		}else {
			V old = w.getElement().getValue();
			tree.setElement(w, newEntry);
			return old;
		}
	}
	
	@TimeComplexity("O(h)")
	@TimeComplexityExpected("O(h)")
	@Override
	/**
	 * Remove method
	 * @param K , V given key
	 */
	public V remove(K key) {
		/* TCJ:
		 * At worse we search through the node with the largest height and
		 * we are also using a while loops which is O(n) + O(log n)
		 */
		// TODO Auto-generated method stub
		Position<Entry<K,V>> v = search(tree.root(), key);

		if(tree.isExternal(v)) 
			return null;
		Position<Entry<K,V>> left = tree.left(v);
		Position<Entry<K,V>> right = tree.right(v);
		V old = v.getElement().getValue();
		if(tree.isExternal(left) && !(tree.isInternal(right))) {
			tree.remove(left);
			tree.remove(v);
		}else if (tree.isExternal(right)){
			tree.remove(right);
			tree.remove(v);
		}else {
			Position<Entry<K,V>> w = right;
			while(tree.isInternal(w)) {
				w = tree.left(w);
			}
			tree.setElement(v, tree.parent(w).getElement());
			Position<Entry<K,V>> successor = tree.parent(w);
			tree.remove(w);
			tree.remove(successor);
		}
		return old;
	}
	

	@TimeComplexity("O(n)")
	/**
	 * Incomplete
	 * @return Iterable <Entry<K,V>> 
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		//for(Position<Entry<K,V>> p: tree.)
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}



}
