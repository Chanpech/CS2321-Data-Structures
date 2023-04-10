import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;
public class Test {

	public static void main(String[] args) {
	MainPlayer P1 = new MainPlayer();
	MainPlayer P2 = new MainPlayer("Micheal Jordan", 5);
	
	P1.setName("Lebron James");
	P1.setLivesLeft(13);
	System.out.println("Player number one name is " +
	P1.getName() + " and have " + P1.getLives());
	
	String name = P1.getName();
	
	System.out.println(name.substring(0, 6));
	
	
	/*
	
	String[] nbaP = {"Lebron", "Zion", "Steph", "Dame", "Kawaii","Melo", "PG13"};
	String[] team = new String[5];
	for(int i = 0; i < 5; i++) {
		System.out.print(nbaP[(int)(Math.random() * nbaP.length)] + " ");
	}
	*/
	

	ArrayList<MainPlayer> myPlayers = new ArrayList<MainPlayer>(); 
	for(int i = 0; i < 6; i++) {
		MainPlayer player = new MainPlayer();
		player.setName("Elite" + i);
		myPlayers.add(player);
	}
	MainPlayer display = new MainPlayer();
	
	for(int r = 1; r < myPlayers.size(); r++) {
		display = myPlayers.get(r);
		System.out.println(display.getName());
	}
	
	}


}
