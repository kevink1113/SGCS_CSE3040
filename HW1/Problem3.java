package cse3040hw1;

import java.util.Scanner;

public class Problem3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter a text: ");
		String text  = scan.nextLine();
		
		while(true) {
			System.out.print("Enter a letter: ");
			String letter = scan.nextLine();
			int count = 0;
			if(letter.length() != 1 || letter.isEmpty())
				System.out.println("You must enter a single letter.");
			else {
				int c = letter.charAt(0);
				for(int i = 0; i<text.length(); i++) {
					if(c == text.charAt(i))
						count++;
				}
				System.out.printf("There are %d %c's in the text.\n", count, c);
				scan.close();
				break;
			}
			
		}

	}

}
