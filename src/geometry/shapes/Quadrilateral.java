// Code authored by Marilyn Croffie
// Ex 9.8 : Quadrilateral Class
// Quadrilateral class represents any shape with four sides
// This is the superclass which other (sub)classes inherit from
// Created 6/27/25
package geometry.shapes;

import geometry.Point;
import geometry.GeometryUtils;

public abstract class Quadrilateral 
{

    private Point p1, p2, p3, p4;

    // Get Methods
    // return first endpoint
    public Point getPoint1()
    {
        return p1;
    }
    
    // return second endpoint
    public Point getPoint2()
    {
        return p2;
    }

    // return third endpoint
    public Point getPoint3()
    {
        return p3;
    }

    // return fourth endpoint
    public Point getPoint4()
    {
        return p4;
    }

    public abstract double getArea();

    public String toString()
    {
        return String.format("%s%n%s%s%n%s%s%n%s%s%n%s%s%n",
            "The four vertices of this quadrilateral are:", "first vertex : ", p1, 
                "second vertex : ", p2, "third vertex : ", p3, "fourth vertex : ", p4);
    }
}
