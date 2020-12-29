package cse3040mp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Text {
	private int[] charCnt = new int[26];
	
	public boolean readTextFromFile(String path) {
		Scanner input = null;
		try {
			input = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found.");
			return false;
		}
	    
		while (input.hasNextLine()) {
			String tmp = input.nextLine();
			tmp = tmp.toLowerCase();
			char[] alphabet = tmp.toCharArray();
			
			for (int i = 0; i< alphabet.length ; i++) {
				if('a' <= alphabet[i] && alphabet[i] <= 'z')
					charCnt[alphabet[i] -'a']++;
			}
		}
		
		return true;
	}
	
	public int countChar(Character c) {
		return charCnt[c-'a'];
	}
}

public class Problem13 {
	public static void main(String[] args) {
		Text t = new Text(); 
		if(t.readTextFromFile("input_prob13.txt")) {
			for(char c = 'a'; c <= 'z'; c++) {
			System.out.println(c + ": " + t.countChar(c)); }
		}
	}

}
