/**
 * 
 */
package cs2321;

import net.datastructures.Queue;

/**
 * CircularArrayQueue implementation using properties 
 * from Queue class. Implement with the help from textbook.
 *
 * @author ruihong-adm
 * @author Chanpech Hoeng
 * @param <E>
 */

public class CircularArrayQueue<E> implements Queue<E> {
	private int f = 0; 
	private int size = 0;
	private E[] data;

	/**
	 * Construct CircularArrayQueue instance
	 * @param queueSize takes in queue size to set as initializer
	 */
	public CircularArrayQueue(int queueSize) {
		// TODO: create a new queue with the specified size
		data = (E[]) new Object[queueSize];
	}

	/**
	 * Returns the number of elements in the queue.
	 * @return number of elements in the queue
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * Tests whether the queue is empty.
	 * @return true if the queue is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 * Inserts an element at the rear of the queue.
	 * @param e  the element to be inserted
	 */
	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		int avail = (f + size)% data.length;
		data[avail] = e;
		size++;
	}

	/**
	 * Returns, but does not remove, the first element of the queue.
	 * @return the first element of the queue (or null if empty)
	 */
	@Override
	public E first() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		return data[f];
	}

	/**
	 * Removes and returns the first element of the queue.
	 * @return element removed (or null if empty)
	 */
	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		E temp = data[f];
		data[f] = null;
		f =  (f+1) % data.length;
		size--;
		return temp;
	}

}
