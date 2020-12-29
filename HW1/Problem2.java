package cse3040hw1;

import java.util.Scanner;

public class Problem2 {

	public static void main(String[] args) {
		int tryNum = 1; 						// number of try
		int guessNum;							// user's guess
		int n = (int)(Math.random()*100 + 1);	// random number
		int min = 1, max = 100;					
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.printf("[%d] Guess a number (%d-%d): ", tryNum, min, max);
			
			guessNum = scan.nextInt();
			
			if (guessNum < min || max < guessNum) {
				System.out.println("Not in range!");
			} else if(guessNum < n) {
				System.out.println("Too small!");
				tryNum++;
				min = guessNum + 1;
			} else if(guessNum > n) {
				System.out.println("Too large!");
				max = guessNum - 1;
				tryNum++;
			}
			
			if(guessNum == n) {
				System.out.printf("Correct! Number of guesses: %d\n", tryNum);
				scan.close();
				break;
			}
		}
		
	}

}
