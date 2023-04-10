package cs2321;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.naming.directory.NoSuchAttributeException;

import net.datastructures.Position;
import net.datastructures.PositionalList;

/**
 * DoublyLinkedList implementation using PositionalList class. 
 * @author ruihong-adm
 * @author Chanpech Hoeng
 * @param <E>
 */
public class DoublyLinkedList<E> implements PositionalList<E> {
	private static class Node<E> implements Position<E>{
		private E element; //Generic element
		private Node<E> next; //next instance variable
		private Node<E> prev; //prev instance variable
		/* Node constructor 
		 * @param e initialize element
		 * @param p initialize previous node
		 * @param n initialize next node
		 */
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		/**
		 * Returns the element at the called node
		 * @return E element type
		 */
		@Override
		public E getElement() throws IllegalStateException {
			if(next == null) {
				throw new IllegalStateException("Position no longer valid");
			}
			return element;
		}
		/**
		 * The methods below this override class are all part of the setter and getter methods.
		 */
		public Node<E> getPrev(){
			return prev;
		}
		public Node<E> getNext(){
			return next;
		}
		public void setElement( E e ){
			element = e;
		}
		public void setPrev(Node<E> e){
			prev = e;
		}
		public void setNext(Node<E> e){
			next = e; 
		}

	}
	private Node<E> header; //Our header node
	private Node<E> tail; //Our tail/tailer node
	private int size = 0; //Initial size

	//DoublyLinkedList default constructor
	public DoublyLinkedList() {
		header = new Node<E>(null,null, null);
		tail = new Node<E>(null,header,null);
		header.setNext(tail);
		
	}

	/**
	 * Check validity of the given position.
	 * @return Node
	 */
	private Node<E> validate(Position<E> p ) throws IllegalArgumentException{
		if(!(p instanceof Node )) {
			throw new IllegalArgumentException("Invalide ");
		}
		Node<E> node = (Node<E>) p;
		if(node.getNext() == null) {
			throw new IllegalArgumentException("p no longer on list");
		}
		return node;
	}

	/**
	 * Check the postion of the node within the list
	 * @return Position<E>
	 */
	private Position<E> position(Node<E> node){
		if(node == header || node == tail) {
			return null;
		}
		return node;
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
	 * Returns the first Position in the list.
	 *
	 * @return the first Position in the list (or null, if empty)
	 */
	@Override
	public Position<E> first() {
		return position(header.getNext());
	}

	/**
	 * Returns the last Position in the list.
	 *
	 * @return the last Position in the list (or null, if empty)
	 */
	@Override
	public Position<E> last() {	
		return position(tail.getPrev());
	}

	/**
	 * Returns the Position immediately before Position p.
	 * @param p   a Position of the list
	 * @return the Position of the preceding element (or null, if p is first)
	 * @throws IllegalArgumentException if p is not a valid position for this list
	 */
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}


	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ ){
		Node<E> newest = new Node<>(e, pred, succ);
		pred.setNext( newest );
		succ.setPrev( newest );
		size++;
		return newest;
	}

	/**
	 * Returns the Position immediately after Position p.
	 * @param p   a Position of the list
	 * @return the Position of the following element (or null, if p is last)
	 * @throws IllegalArgumentException if p is not a valid position for this list
	 */
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {	
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	/**
	 * Inserts an element at the front of the list.
	 *
	 * @param e the new element
	 * @return the Position representing the location of the new element
	 */
	@Override
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());
	}


	/**
	 * Inserts an element at the back of the list.
	 *
	 * @param e the new element
	 * @return the Position representing the location of the new element
	 */
	@Override
	public Position<E> addLast(E e) {
		return addBetween(e, tail.getPrev(), tail);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	/**
	 * Inserts an element immediately before the given Position.
	 *
	 * @param p the Position before which the insertion takes place
	 * @param e the new element
	 * @return the Position representing the location of the new element
	 * @throws IllegalArgumentException if p is not a valid position for this list
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}


	/**
	 * Replaces the element stored at the given Position and returns the replaced element.
	 *
	 * @param p the Position of the element to be replaced
	 * @param e the new element
	 * @return the replaced element
	 * @throws IllegalArgumentException if p is not a valid position for this list
	 */
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}

	/**
	 * Removes the element stored at the given Position and returns it.
	 * The given position is invalidated as a result.
	 *
	 * @param p the Position of the element to be removed
	 * @return the removed element
	 * @throws IllegalArgumentException if p is not a valid position for this list
	 */
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E temp = node.getElement();
		node.setElement(null);
		node.setPrev(null);
		node.setNext(null);
		return temp;
	}

	class MyIterator implements Iterator<E>{
		private Node<E> current = header.getNext();
		
		@Override
		public boolean hasNext() {
			return current.getNext() != null;
		}

		@Override
		public E next(){
			if(!(hasNext())){
				throw new NoSuchElementException();
			}
			E temp = current.getElement();
			current = current.getNext();
			return temp;
		}
	}
	/**
	 * Inner class, PositionalIterator,which implements the iterator class
	 */
	private class PositionaIterator implements Iterator<Position<E>>{
		private Position<E> cursor = first();
		private Position<E> recent = null;
		@Override
		public boolean hasNext() {	
			return (cursor != null);
		}

		@Override
		public Position<E> next() {	
			recent = cursor;
			cursor = after(cursor);
			return recent;
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
	 * Inner class Position Iterable which implement the Iterable class. 
	 */
	public class PositionIterable implements Iterable<Position<E>>{

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionaIterator();
		}

	}

	/**
	 * Returns the positions of the list in iterable form from first to last.
	 * @return iterable collection of the list's positions
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	/**
	 * Remove the first element within the list
	 * @return E the first removed Node within the list
	 * @throws IllegalArgument Exception
	 */
	public E removeFirst() throws IllegalArgumentException {
		Node<E> node = header.getNext();
		Node<E> successor = node.getNext();
		header.setNext(successor);
		successor.setPrev(header);
		E temp = node.getElement();
		node.setElement(null);
		node.setPrev(null);
		node.setNext(null);
		size--;
		return temp;
	}

	/**
	 * Remove the last element within the list
	 * @return E the last removed Node within the list
	 * @throws IllegalArgument Exception
	 */
	public E removeLast() throws IllegalArgumentException {
		Node<E> node = tail.getPrev();
		Node<E> previous = node.getPrev();
		tail.setPrev(previous);
		previous.setNext(tail);
		E temp = node.getElement();
		node.setElement(null);
		node.setPrev(null);
		node.setNext(null);
		size--;
		return temp;
	}

}
