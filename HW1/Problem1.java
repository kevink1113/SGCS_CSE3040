package cse3040hw1;

import java.util.Scanner;

public class Problem1 {

	public static void main(String[] args) {
		System.out.print("ASCII code teller. Enter a letter: ");
		
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		
		if(input.length() != 1 || input.isEmpty()) {
			System.out.println("You must input a single uppercase or lowercase alphabet.");
			scan.close();
		}
		
		else {
			char c = input.charAt(0);
			if(('a' <= c && c <= 'z') || ('A'<= c && c <= 'Z')) {
				System.out.println("The ASCII code of C is " + (int)c + ".");
			}
			else {
				System.out.println("You must input a single uppercase or lowercase alphabet.");
			}
			scan.close();
		}
		
	}

}
