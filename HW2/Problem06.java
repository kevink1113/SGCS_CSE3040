package cse3040hw2;

interface IntSequence {
	boolean hasNext();
	int next();
}

class FibonacciSequence implements IntSequence {
	private int numberPast = -1;
	private int number = 1;
	public boolean hasNext() { return true; }
	public int next() {
		int res = numberPast + number;
		numberPast = number;
		number = res;
		
		return number;
	}
}

public class Problem06 {
	public static void main(String[] args) {
		IntSequence seq = new FibonacciSequence(); 
		for(int i=0; i<20; i++) {
			if(seq.hasNext() == false) break;
			System.out.print(seq.next() + " "); 
		}
		System.out.println(" ");
	}
}
