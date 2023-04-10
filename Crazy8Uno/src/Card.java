
public class Card {
	private String suit;
	private int value;
	
	public Card(String s, int v) {
		suit = s;
		value = v;
		
	}
	public int getValue() {
		return value;
	}
	public String getSuit() {
		return suit;
	}
	public String toString() {
		return value + " of " + suit + "s";
	}
}
