package cse3040hw1;

import java.util.Scanner;

public class Problem4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter a text: ");
		String text  = scan.nextLine();
		
		while(true) {
			System.out.print("Enter a string: ");
			String string = scan.nextLine();
			
			if(string.length() == 0)
				System.out.println("You must enter a string.");
			else {
				int count = 0;
				for(int i=0; i<text.length()-string.length()+1; i++) {
					if(text.substring(i, i+string.length()).equals(string)) {
						count++;
					}
				}
				
				System.out.printf("There are %d instances of \"%s\".\n", count, string);
				scan.close();
				break;
			}
			
		}

	}

}
