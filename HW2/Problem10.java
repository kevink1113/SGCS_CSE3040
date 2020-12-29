package cse3040hw2;

class Points {
	private int len;
	private double point[];
	private double sum;
	Points(double point[]) {
		this.len = point.length;
		this.point = point;
		this.sum = 0;
		for(int i = 0; i< this.len; i++)
			sum += this.point[i];
	}
	public int getLen() { return this.len; }
	public double[] getPoints() { return this.point; }

	public String toString() {
		return "[sum of points: " + this.sum + "]";
	}
	
	public boolean equals (Points pointComp) {
		if (this == pointComp) return true;
		if (pointComp == null) return false;
		if (getClass() != pointComp.getClass()) return false;
		Points other = (Points)pointComp;
		return (this.sum == other.sum);
	}
	
}

public class Problem10 {
	public static void main(String[] args) {
		Points p1 = new Points(new double[] {1.0, 2.0, 3.0}); 
		Points p2 = new Points(new double[] {4.0, 5.0, 6.0}); 
		System.out.println(p1);
		System.out.println(p2); 
		System.out.println(p1.equals(p2));
		Points p3 = new Points(new double[] {1.0, 4.0, 7.0}); 
		Points p4 = new Points(new double[] {3.0, 9.0}); 
		System.out.println(p3);
		System.out.println(p4); 
		System.out.println(p3.equals(p4));
		Points p5 = new Points(new double[] {1.0, 2.0}); 
		Points p6 = null;
		System.out.println(p5);
		System.out.println(p6); 
		System.out.println(p5.equals(p6));
	}
}
