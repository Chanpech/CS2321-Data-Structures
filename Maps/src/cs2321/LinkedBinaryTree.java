package cs2321;
import java.util.Iterator;

import net.datastructures.*;


/**
 * LinkedBinaryTree data structure that use sentinel nodes that is null.
 * To make adding data easier. 
 * @author Chanpech Hoeng
 * @param <E>
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>{

	public static class Node<E> implements Position<E>{
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;

		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}

		@Override
		public E getElement() throws IllegalStateException {
			// TODO Auto-generated method stub
			return element;
		}
		public Node<E> getParent(){
			return parent;
		}
		public Node<E> getLeft(){
			return left;
		}
		public Node<E> getRight(){
			return right;
		}
		public void setElement(E e) {
			element = e;
		}
		public void setParent(Node<E> p) {
			parent = p;
		}
		public void setLeft(Node<E> l) {
			left = l;
		}
		public void setRight(Node<E> r) {
			right = r;
		}
	}

	private Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
		return new Node<E>(e, parent,left, right);
	}

	private Node<E> root = null;
	private int size = 0;

	@Override
	public Position<E> root() {
		// TODO Auto-generated method stub
		return root;
	}

	public  LinkedBinaryTree( ) {
		//TODO default constructor
	}

	private Node<E> validate(Position<E> p )throws IllegalArgumentException{
		if(!(p instanceof Node)) {
			throw new IllegalArgumentException("Not valid position type");
		}
		Node<E> node = (Node<E>)p;
		if(node.getElement() == node) {
			throw new IllegalArgumentException("p is no longer in the tree");
		}
		return node;
	}
	
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return node.getParent();
	}
	
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return node.getLeft();
	}

	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return node.getRight();
	}

	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		if(right(p)!= null || left(p) != null)
			return true;
		return false;
	}

	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		if(left(p) == null && left(p) == null)
			return true;
		return false;
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

	/* creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public Position<E> addRoot(E e) throws IllegalStateException {
		//TODO: implement this method
		if(!isEmpty()) throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}


	/* creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		//TODO: implement this method
		Node<E> parent = validate(p);
		if(parent.getLeft() != null) {
			throw new IllegalArgumentException("There's already element in the left child of p");
		}
		Node<E> child = createNode(e, parent, null,null);
		parent.setLeft(child);
		size++;
		return child;
	}

	/* creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		//TODO: implement this method
		Node<E> parent = validate(p);
		if(parent.getRight() != null) {
			throw new IllegalArgumentException("There's already exist an elment in the right child");
		}
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}

	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public void setElement(Position<E> p, E element) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(element);
	}


	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the in-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public List<E> inOrderElements(Position<E> p) {
		//Hint: List is an interface. ArrayList implements List. 
		Node<E> node = validate(p);
		List<E> list = new ArrayList<E>();
		Position<E> v = p;
		
		return null;
	}
	

	/**
	 * If p has two children, throw IllegalAugumentException. 
	 * If p is an external node ( that is it has no child), remove it from the tree.
	 * If p has one child, replace it with its child. 
	 * If p is root node, update the root accordingly. 
	 * @param p who has at most one child. 
	 * @return the element stored at position p if p was removed.
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(n)")
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> w = null;
		Node<E> node = validate(p);
		if(node.getLeft() == null) {
			w = node.getLeft();
		}else if(node.getRight() == null) {
			w = node.getRight();
		}
		if(node == root){
			root = w;
		}
		else if(node.getParent().getLeft() == w) {
			node.getParent().setLeft(w);
		}else {
			node.getParent().setRight(w);
		}

		if(w != null) {
			w.setParent(node.getParent());
		}
		size--;
		return node.getElement();
	}
}
