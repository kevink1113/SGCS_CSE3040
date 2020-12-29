package cse3040mp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Fruit { 
	private String name = "";
	private double price = 0;
	public Fruit (String name, double price) {
		this.name = name;
		this.price = price;
	}
	public String getName() { return this.name; }
	public double getPrice() { return this.price; }
}

class Box<T> {
	ArrayList<T> list = new ArrayList<T>();
	void add(T a) { list.add(a); }
}

class FruitBox<T extends Fruit> extends Box<T> {
	void add(T a) { 
		list.add(a);
		System.out.println(a.getName() + " " + a.getPrice());
	}
	public String getMaxItem() {
		int maxIdx = 0;
		for(int i = 0; i < list.size(); i++) 
			if(list.get(i).getPrice() > list.get(maxIdx).getPrice()) 
				maxIdx = i;
		
		return list.get(maxIdx).getName();
	} 
	public double getMaxPrice() {
		int maxIdx = 0;
		for(int i = 0; i < list.size(); i++) 
			if(list.get(i).getPrice() > list.get(maxIdx).getPrice()) 
				maxIdx = i;
			
		return list.get(maxIdx).getPrice();
	}
	public String getMinItem() {
		int minIdx = 0;
		for(int i = 0; i < list.size(); i++) 
			if(list.get(i).getPrice() < list.get(minIdx).getPrice()) 
				minIdx = i;
		
		return list.get(minIdx).getName();
	} 
	public double getMinPrice() {
		int minIdx = 0;
		for(int i = 0; i < list.size(); i++) 
			if(list.get(i).getPrice() < list.get(minIdx).getPrice()) 
				minIdx = i;
			
		return list.get(minIdx).getPrice();
	}
	public double getAvgPrice() { 
		double sum = 0;
		for(int i = 0; i < list.size(); i++)
			sum += list.get(i).getPrice();
		
		return sum / list.size(); 
	}
	public int getNumItems() { return list.size(); }
}

class ItemReader {
	static boolean fileToBox(String file, Box<Fruit> box) {
		Scanner input = null;
		try {
			input = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found.");
			return false;
		}
	    
		while (input.hasNextLine()) {
			String in = input.nextLine();
			String[] temp;
			String delimiter = " ";
			temp = in.split(delimiter);   
			String name = temp[0];   
			double price = Double.parseDouble(temp[1]);
			
			box.add(new Fruit(name, price));
		}
		
		return true;
	}
}

public class Problem14 {
	public static void main(String[] args) {
		FruitBox<Fruit> box = new FruitBox<>();
		boolean rv = ItemReader.fileToBox("input_prob14.txt", box);
		if(rv == false) return;
		box.add(new Fruit("orange", 9.99)); 
		System.out.println("----------------");
		System.out.println(" Summary"); 
		System.out.println("----------------");
		System.out.println("number of items: " + box.getNumItems()); System.out.println("most expensive item: " + box.getMaxItem() + " (" +
		box.getMaxPrice() + ")");
		System.out.println("cheapest item: " + box.getMinItem() + " (" + box.getMinPrice() + ")");
		System.out.printf("average price of items: %.2f", box.getAvgPrice());}
}
