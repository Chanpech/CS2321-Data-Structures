package cs2321;

import net.datastructures.List;
/**
 * @author Chanpech Hoeng
 * @param <E>
 */
public class Josephus{
	private CircularArrayQueue<String> list;
	/**
	 * All persons sit in a circle. When we go around the circle, initially starting
	 * from the first person, then the second person, then the third... 
	 * we count 1,2,3,.., k-1. The next person, that is the k-th person is out. 
	 * Then we restart the counting from the next person, go around, the k-th person 
	 * is out. Keep going the same way, when there is only one person left, she/he 
	 * is the winner. 
	 *  
	 * @parameter persons  an array of string which contains all player names.
	 * @parameter k  an integer specifying the k-th person will be kicked out of the game
	 * @return return a list in the order when the players were out of the game. 
	 *         The last one in the list is the winner.  
	 */
	public List<String> order(String[] persons, int k ) {
		list = new CircularArrayQueue<String>(persons.length); //Initialize list
		ArrayList<String> storeList = new ArrayList<String>(); //StoreList
		
		for(int i = 0; i < persons.length; i++ ) {//Iterate to n-1
			list.enqueue(persons[i]);//convert into list
		}
		
		while(list.size() > 1) { //Iterator to 2
			for(int i = 1; i <= k-1; i++) {//Nest loop that iterator to the k values represent counting
				list.enqueue(list.dequeue());
			}
			storeList.addLast(list.dequeue());
		}
		storeList.addLast(list.dequeue());
		
		return storeList;
	}	
}
