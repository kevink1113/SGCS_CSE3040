package cse3040hw1;

import java.util.Scanner;

public class Problem5 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int arr[] = new int[5];
		System.out.println("enter exam scores of each student.");
		
		for(int i =0; i<5; i++) {
			System.out.printf("Score of student %d: ", i+1);
			arr[i] = scan.nextInt();
		}
		scan.close();
		
		int maxIdx=0; int max2Idx=0;
		
		for(int i =0; i<5; i++) {
			if (arr[maxIdx] < arr[i]) maxIdx = i;
		}
		for(int i =0; i<5; i++) {
			if (i == maxIdx) continue;
			if (arr[max2Idx] < arr[i]) max2Idx = i;
		}
		
		System.out.printf("The 1st place is student %d with %d points.\n", maxIdx+1, arr[maxIdx]);
		System.out.printf("The 2nd place is student %d with %d points.\n", max2Idx+1, arr[max2Idx]);

	}

}
