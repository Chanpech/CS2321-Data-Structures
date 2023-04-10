package cs2321;

import net.datastructures.Stack;
/**
 * @author Chanpech Hoeng
 * @param <E>
 */
public class DLLStack<E> implements Stack<E> {
	
	private DoublyLinkedList<E> object= init();
	//default constructor
	public DoublyLinkedList<E> init(){
		return new DoublyLinkedList<E>();
	}
	
	//return size of stack
	@Override
	public int size() {
		return object.size();
	}
	//return comparison result of size
	@Override
	public boolean isEmpty() {
		return object.size() != 0;
	}
	//push an element onto stack
	@Override
	public void push(E e) {
		object.addLast(e);	
	}
	//return with out remove the value of the last element
	@Override
	public E top() {
		if(object.size() == 0) {//Avoid Null pointer
			return null;
		}
		return object.last().getElement();
	}
	//return the popped element
	@Override
	public E pop() {
		if(object.size() == 0) {//Avoid null pointer
			return null;
		}
		E temp = object.last().getElement();
		object.removeLast();
		return temp;
	}

}
