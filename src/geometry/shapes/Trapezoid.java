// Code authored by Marilyn Croffie
// Ex 9.8 : Trapezoid Class
// Trapezoid class represents a four-sided shape, quadrilateral,
// with a pair of parallel opposite sides
// This (sub)class inherits from the Quadrilateral (super)class
// Created 6/28/25
package geometry.shapes;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Trapezoid extends Quadrilateral 
{
    private HashMap<Double, List<List<Point>>> slopes;
    private HashMap<Double, Integer> parallelPairCounts;
    private List<Point> parallelSegmentPoints;

    public Trapezoid(Point p1, Point p2, Point p3, Point p4)
    {
        // Initialize using superclass constructor
        super(p1, p2, p3, p4);
    }

    // Helper Method(s)
    // validates point by checking for:
    // i. duplicates,
    // ii. collinearity, and
    // iii. parallel sides
    @Override
    protected Point[] validate(Point[] points)
    {
        super.validate();
        
        if (!isParallelOrder(points))
        {
            points = new Point[]{points[1], points[2], points[3], points[0]};
            if (!isParallelOrder(points))
            {
                throw new IllegalArgumentException("Points do not form a quadrilateral with parallel sides");
            }
        }

        return points;
    }

    @Override
    // returns the area of a trapezoid
    public double getArea()
    {
        double base1 = distance(toVector(getPointP1(), getPointP2()));
        double base2 = distance(toVector(getPointP3(), getPointP4()));

        double height = height(getPointP1(), getPointP2(), getPointP3());

        double area = ((base1 + base2)/2) * height;
       
        return area;
    }

}


