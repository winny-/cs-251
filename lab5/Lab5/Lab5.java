package Lab5;

import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Lab5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;

        out.print("Enter number of points: ");
        int n = in.nextInt(); 
        Point[] points = new Point[n];
		
        in.nextLine(); // clear input buffer

        for(int i=0; i<n; i++) {
            out.print("Enter x and y values: ");
            String[] xy = in.nextLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            points[i] = new Point(x, y);
        }
		
        largestDistance(points);
		
        in.close();
    }
	
    // Print all points and mark two points with the longest distance with *
    static void largestDistance(Point[] points) {
        Line longestLine = new LineCollection(points).longestLine();
		
        for(int i=0; i < points.length; i++) {
            Point point = points[i];
            System.out.printf((longestLine.contains(point) ? "*" : " ") + "%s\n", point);
        }
    }

    @Test
    public void testDistance() {
        assertEquals(0, new Point(1, 1).distance(new Point(1, 1)), 1);
    }

    @Test
    public void testFact() {
        assertEquals(0, LineCollection.fact(0));
        assertEquals(1, LineCollection.fact(1));
        assertEquals(2, LineCollection.fact(2));
        assertEquals(6, LineCollection.fact(3));
        assertEquals(120, LineCollection.fact(5));
    }

    @Test
    public void testnCr() {
        assertEquals(10, LineCollection.nCr(5, 2));
        assertEquals(120, LineCollection.nCr(10, 3));
    }
}

// A collection of lines
class LineCollection {
    private Line[] lines;
	
    // Create a collection of lines by pairing up points in a point array
    // Do not include lines from a point to itself
    // Do not include both a line and its opposite (e.g. (p1, p2) and (p2, p1) are opposite of each other. Only keep one)
    LineCollection(Point[] points) {
        this(combinations(points));
    }

    // Save the parameter lines in the field lines
    LineCollection(Line[] lines) {
        assert(lines != null && lines.length > 0);
        this.lines = lines;
    }
	
    // Return the line with the longest distance in this collection
    Line longestLine() {
        Line longest = null;
        for (Line line : this.lines) {
            if (longest == null || line.length() > longest.length()) {
                longest = line;
            }
        }
        return longest;
    }

    static Line[] combinations(Point[] points) {
        assert(points.length > 1);
        Line[] lines = new Line[nCr(points.length, 2)];
        int idx = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                lines[idx++] = new Line(points[i], points[j]);
            }
        }
        return lines;
    }

    static int nCr(int n, int r) {
        return fact(n) / (fact(r) * fact(n - r));
    }

    static int fact(int n) {
        if (n == 0) {
                return 0;
        }
        int m = 1;
        for (int i = 1; i <= n; i++) {
            m *= i;
        }
        return m;
    }

    // All of my why.
    static Line[] stridedPointsToLines(Point[] points) {
        assert(points.length % 2 == 0 && points.length > 0);
        int nLines = points.length / 2;
        Line tempLines[] = new Line[nLines];
        for (int idx = 0; idx < nLines; idx++) {
            int baseOffset = idx * 2;
            tempLines[idx] = new Line(points[baseOffset],
                                      points[baseOffset + 1]);
        }
        return tempLines;
    }
}

class Line {
    private final Point start, end;
    private double length;
	
    // A line consists of two points: start and end
    // Compute and save the distance between the two points in the field "length"
    Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.length = start.distance(end);
    }
	
    // Return length
    double length() {
        return this.length;
    }

    // Return true if and only if p is either the start or the end point of this line
    boolean contains(Point p) {
        return p.equals(this.start) || p.equals(this.end);
    }
	
    // Return string representation of a line: e.g. ((1, 2), (2, 3)) where (1, 2) is the start and (2, 3) is the end point
    public String toString() {
        return String.format("(%s, %s)", this.start, this.end);
    }
}

class Point {
    private final int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Return string represention of a point: e.g. (1, 2) is a point with x = 1 and y = 2
    public String toString() { 
        return String.format("(%d, %d)", this.x, this.y);
    }

    // Return the Euclidean distance between this point and "that" point.
    //  Hint: Math class has sqrt method
    double distance(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) +
                         Math.pow(this.y - that.y, 2));
    }
	
    // Return true if and only if "that" is a Point object and
    //  the x, y coordinates of this point is the same as the x, y coordinates of "that".
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Point) {
            Point thatLine = (Point)that;
            return this.x == thatLine.x &&
                this.y == thatLine.y;
        }
        return false;
    }
}
