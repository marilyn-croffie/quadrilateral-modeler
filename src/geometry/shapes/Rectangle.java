// Code authored by Marilyn Croffie
// Ex 9.8 : Rectangle Class
// Rectangle class represents a four-angled shape, quadrilateral,
// with two pairs of parallel, congruent, opposite angles and 
// four congruent (right) angles
// This (sub)class inherits from the Parallelogram (super)class
// Created 6/28/25
package geometry.shapes;

import geometry.Point;
import static geometry.GeometryUtils.*;
import java.util.Arrays;

public class Rectangle extends Parallelogram 
{
    public Rectangle(Point p1, Point p2, Point p3, Point p4)
    {
        // Initialize using superclass constructor
        super(p1, p2, p3, p4);
    }

    // Helper Method(s)
    // checks for congruent angles
    @Override
    private Point[] validate(Point[] points)
    {
        points = super.validate(points);
        
        if (!areAllRightAngles())
        {
            throw new IllegalArgumentException("Points do not form a rectangle");
        }

        return points;
    }

    @Override
    public double getArea()
    {
        // Find two adjacent perpendicular sides
        double length = distance(getP1(), getP2());
        double breadth = distance(getP2(), getP3());
        
        double area = length*breadth;

        return area;
    }

}
