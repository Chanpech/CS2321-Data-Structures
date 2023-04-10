package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Chanpech Hoeng
 * Test cases for the Josephus games
 * Test edge cases as well
 */
public class JosephusTest {
	
	private Josephus T = init();
	//Default initiator constructor
	public Josephus init() {
		
		return new Josephus();
	}
	@Before
	public void setUp() throws Exception {
		
	}
	//Test for position at 0 after order()
	@Test
	public void testOrder1() {
		String[] persons = {"A", "B", "C", "D", "E", "F"};
		int key = 3;
		ArrayList<String> target = new ArrayList<String>();
		target.addLast("C");
		target.addLast("F");
		target.addLast("D");
		target.addLast("B");
		target.addLast("E");
		target.addLast("A");
		org.junit.Assert.assertEquals("order() is 3", target.get(0), T.order(persons, key).get(0));
	}
	//Test for size after order()
	@Test
	public void testOrder2() {
		String[] persons = {"A", "B", "C", "D", "E", "F"};
		int key = 3;
		org.junit.Assert.assertEquals("size() is 6", persons.length, T.order(persons, key).size());
	}
	//Test for position at 3 after order()
	@Test
	public void testOrder3() {
		String[] persons = {"A", "B", "C", "D"};
		int key = 1;
		ArrayList<String> target = new ArrayList<String>();
		target.addLast("A");
		target.addLast("B");
		target.addLast("C");
		target.addLast("D");
		org.junit.Assert.assertEquals("order() is 3", target.get(3), T.order(persons, key).get(3));
	}
	//Test for position at 1 after order()
	@Test
	public void testOrder4() {
		String[] persons = {"A", "B", "C", };
		int key = 2;
		ArrayList<String> target = new ArrayList<String>();
		target.addLast("B");
		target.addLast("A");
		target.addLast("C");
		org.junit.Assert.assertEquals("order() is 3", target.get(1), T.order(persons, key).get(1));
	}

}

