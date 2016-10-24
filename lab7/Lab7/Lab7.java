package Lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Lab7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PointList lst = new PointList();
		
        while(true) {
            System.out.print("Enter x, y values (type 0 0 to exit): ");
            int x = in.nextInt();
            int y = in.nextInt();
			
            if (x == 0 && y == 0) {
                break;
            }
            in.nextLine();
			
            if(lst.insert(x, y)) {
                System.out.println(lst); 
            }
			 
        }
        in.close();
    }
}

//////////////////////////////////////////////////////////////////////

class PointList {
    private List<Point> points = new ArrayList<>();
    private static final DecimalFormat df = new DecimalFormat("#.##");
	
    boolean insert(int x, int y) {
        Point p = new Point(x, y);
        int i; // Keep i in scope enclosing the for loop.
        for (i = 0; i < this.points.size(); i++) {
            Point other = this.points.get(i);
            int order = p.compareTo(other);
            if (order == 0) { // p already exits.
                return false;
            } else if (order == -1) { // p is less than other.
                break;
            }
        }
        // i is the last element's index if the loop isn't breaked out of.
        // Otherwise i is the first element to be greater than p.
        this.points.add(i, p);
        return true;
    }

    @Override
    public String toString() {
        int size = this.points.size();
        String[] parts = new String[size+1]; // +1 for "" to add trailing newline on String.join().
        parts[size] = "";
        for (int i = 0; i < size; i++) {
            Point p = this.points.get(i);
            parts[i] = String.format("%s; distance to origin: %s",
                                     p,
                                     this.df.format(p.distanceToOrigin()));
        }
        return String.join("\n", parts);
    }

    // For unit tests.
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof PointList) {
            return ((PointList)other).points.equals(this.points);
        }
        return false;
    }
}

//////////////////////////////////////////////////////////////////////

class Point implements Comparable<Point> {
    private int x, y;
    private static Point zero = new Point(0, 0);
	
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
	
    double distance(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) +
                         Math.pow(this.y - that.y, 2));
    }
	
    double distanceToOrigin() {
        return distance(zero);
    }

    // Fixed.
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Point) {
            Point p = (Point)that;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    // For deterministic sorting of Point.
    public int compareTo(Point other) {
        if (this.equals(other)) {
            return 0;
        } else if (this.distanceToOrigin() == other.distanceToOrigin()) {
            if (this.x != other.x) {
                return this.x < other.x ? -1 : 1;
            } else {
                return this.y < other.y ? -1 : 1;
            }
        }
        return this.distanceToOrigin() < other.distanceToOrigin() ? -1 : 1;
    }

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
