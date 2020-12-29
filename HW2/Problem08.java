package cse3040hw2;

class Shape {
	private double area = 0;
	public void setArea(double area) { this.area = area; }
	public double getArea() { return this.area; }
}

class Circle extends Shape {
	private double r;
	public Circle(double r) {
		this.r = r;
		super.setArea(this.r * this.r * Math.PI);
	}
}

class Square extends Shape {
	private double a;
	public Square(double a) {
		this.a = a;
		super.setArea(this.a * this.a);
	}
}

class Rectangle extends Shape {
	private double a, b;
	public Rectangle(double a, double b) {
		this.a = a;
		this.b = b;
		super.setArea(this.a * this.b);
	}
}

public class Problem08 {
	private static double sumArea(Shape[] arr) {
		double sum = 0;
		for(int i = 0; i < 4; i++)
			sum += arr[i].getArea();
		
		return sum;
	}
	
	public static void main(String[] args) {
		Shape[] arr = { new Circle(5.0), new Square(4.0),
				new Rectangle(3.0, 4.0), new Square(5.0)}; 
		System.out.println("Total area of the shapes is: " + sumArea(arr));
	}
}
