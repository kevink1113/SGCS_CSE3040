package cse3040hw2;

class Point {
	private int len;
	private double coor[];
	Point(double coor[]) {
		this.len = coor.length;
		this.coor = coor;
	}
	public int getLen() { return this.len; }
	public double[] getPoint() { return this.coor; }
}

class EuclideanDistance {
	static double getDist (Point p1, Point p2) {
		double dis = 0;
		double[] coor1 = p1.getPoint();
		double[] coor2 = p2.getPoint();
		if (p1.getLen() != p2.getLen()) return -1;
		
		for(int i = 0; i < p1.getLen(); i++)
			dis += Math.pow(coor1[i] - coor2[i], 2);
		
		return Math.sqrt(dis);
	}
}

class ManhattanDistance {
	static double getDist (Point p1, Point p2) {
		double dis = 0;
		double[] coor1 = p1.getPoint();
		double[] coor2 = p2.getPoint();
		if (p1.getLen() != p2.getLen()) return -1;
		
		for(int i = 0; i < p1.getLen(); i++)
			dis += Math.abs(coor1[i] - coor2[i]);
		
		return dis;
	}
}

public class Problem09 {
	public static void main(String[] args) {
		Point p1 = new Point(new double[] {1.0, 2.0, 3.0});
		Point p2 = new Point(new double[] {4.0, 5.0, 6.0});
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p1, p2)); 
		System.out.println("Manhattan Distance: " + ManhattanDistance.getDist(p1, p2)); 
		Point p3 = new Point(new double[] {1.0, 2.0, 3.0});
		Point p4 = new Point(new double[] {4.0, 5.0});
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p3, p4)); 
		System.out.println("Manhattan Distance: " + ManhattanDistance.getDist(p3, p4)); 
	}
}
