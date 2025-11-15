// Code authored by Marilyn Croffie
// Ex 9.8 : Square Class
// Square class represents a four-sided shape, quadrilateral,
// with two pairs of parallel opposite sides and four congruent
// sides and (right) angles
// This (sub)class inherits from the Rectangle (super)class
// Created 6/28/25
package geometry.shapes;

import geometry.Point;
import static geometry.GeometryUtils.*;

public class Square extends Rectangle 
{
    public Square(Point p1, Point p2, Point p3, Point p4)
    {
        // Initialize using superclass constructor
        super(p1, p2, p3, p4);
    }
    
    @Override
    protected Point[] validate(Point[] points)
    {
        points = super.validate(points);

        if (!allSidesEqual(points))
        {
            throw new IllegalArgumentException("Points do not form a square");
        }

        return points;
    }

}
