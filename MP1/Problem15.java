package cse3040mp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Item {
	private String word;
	private int appear;
	public Item(String string, int i) {
		this.word = string;
		this.appear = i;
	}
	public String getWord() { return this.word; }
	public int getAppear() { return this.appear; } 
	
	public void incAppear() { this.appear += 1; }
	public String toString() { return this.word + " " + this.appear; }
}

class MyFileReader {
	public static boolean readDataFromFile(String file, ArrayList<Item> list) {
		Scanner input = null;
		try {
			input = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			return false;
		}
	    
		while (input.hasNextLine()) {
			String tmp = input.nextLine();
			tmp = tmp.toLowerCase();			
			String[] temp;
			String delimiter = " ";
			temp = tmp.split(delimiter);
			
			for(int i = 0; i< temp.length; i++)
				list.add(new Item(temp[i], 1));
			
			for(int i = 0; i< list.size(); i++) {
				for(int j = i+1; j < list.size(); j++) {
					if(list.get(i).getWord().equals(list.get(j).getWord())) {
						list.get(i).incAppear();
						list.remove(j);
					}
				}
			}
		}
		
		return true;
	}
}

public class Problem15 {
	public static void main(String[] args) {
		ArrayList<Item> list = new ArrayList<>();
		boolean rv = MyFileReader.readDataFromFile("input_prob15.txt", list); 
		if(rv == false) {
			System.out.println("Input file not found.");
			return; 
		}
		for(Item it: list) System.out.println(it);
	}
}
