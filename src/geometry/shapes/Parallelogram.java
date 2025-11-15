// Code authored by Marilyn Croffie
// Ex 9.8 : Parallelogram Class
// Parallelogram class represents a four-sided shape, quadrilateral,
// with two pairs of parallel, congruent, opposite sides and angles
// This (sub)class inherits from the Trapezoid (super)class
// Created 6/28/25
package geometry.shapes;

import geometry.Point;
import static geometry.GeometryUtils.*;
import java.util.Arrays;
import java.util.List;

public class Parallelogram extends Trapezoid 
{
    public Parallelogram(Point p1, Point p2, Point p3, Point p4)
    {
        // Initialize using superclass constructor
        super(p1, p2, p3, p4);
    }

    // Helper Method(s)
    // checks for congruence
    @Override
    protected Point[] validate(Point[] points)
    {
        points = super.validate(points);
        
        if (!areParallel(points[2], points[3]))
        {
            throw new IllegalArgumentException("Points do not form a quadrilateral with two parallel sides");
        }

        return points;     
    }

    @Override
    public double getArea()
    {
        double base = distance(getP1(), getP2());
        double height = height(getP1(), getP2(), getP3());

        double area = base*height;

        return area;
    }
}
