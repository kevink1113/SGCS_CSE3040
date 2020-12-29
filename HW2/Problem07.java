package cse3040hw2;

import java.util.Scanner;

interface IntSequenceStr {
	boolean hasNext();
	int next();
}

class BinarySequenceStr implements IntSequenceStr {
	private int a, number;
	public BinarySequenceStr(int number) { 
		this.number = number;
		a = (int)Math.pow(2, 30);
		while(a != 0) {
			if((this.number & a) > 0) break;
			a /= 2;
		} 
	} 
	
	public boolean hasNext() { return a >= 1; }
	
	public int next() {
		int tmp = this.number & (int)a;
		a /= 2;
		if (tmp>0) return 1;
		else return 0;
	}
}

public class Problem07 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 
		System.out.print("Enter a positive integer: ");
		String str = in.nextLine();
		int num = Integer.parseInt(str);
		in.close();
		System.out.println("Integer: " + num); 
		IntSequenceStr seq = new BinarySequenceStr(num); 
		System.out.print("Binary number: "); 
		while(seq.hasNext()) System.out.print(seq.next()); 
		System.out.println(" ");
	}
}
