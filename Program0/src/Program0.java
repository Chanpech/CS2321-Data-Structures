/**
 * Finding the perimeter of a triangle by entering three values.
 * Date Last Modified: 08/31/2020
 * Chanpech Hoeng
 * choeng@mtu.edu
 * CS1131, Fall 2020
 * Lab Section ?
 * */

import java.util.Scanner;

public class Program0 {

	/**
	 * Boot strap method - acceapts sides of triangle and prints perimeter if valid
	 * 
	 * @param args - String array of command line arguemnts: IGNORED
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Finding the perimeter of a triangle.\nEnter the three values one at the time.");
		System.out.println("Enter an edge values: ");
		double edge1 = scan.nextDouble();
		System.out.println("Enter an edge values: ");
		double edge2 = scan.nextDouble();
		System.out.println("Enter an edge values: ");
		double edge3 = scan.nextDouble();
		if ( edge1 + edge2 > edge3 && edge3 + edge1 > edge2 && edge2 + edge3 > edge1 ) {
			double perimeter = edge1 + edge2 + edge3;
			System.out.println("The perimeter is: " + (Math.round(perimeter * 10.0) / 10.0));
		} else {
			System.out.println("Input entered are invalid!");
		}
	}
}
