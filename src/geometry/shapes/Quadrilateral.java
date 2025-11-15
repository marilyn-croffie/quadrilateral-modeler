// Code authored by Marilyn Croffie
// Ex 9.8 : Quadrilateral Class
// Quadrilateral class represents any shape with four sides
// This is the superclass which other (sub)classes inherit from
// Created 6/27/25
package geometry.shapes;

import geometry.Point;
import static geometry.GeometryUtils.*;

public abstract class Quadrilateral 
{
    private Point[] pts;

    public Quadrilateral(Point p1, Point p2, Point p3, Point p4)
    {
        Point[] pts = new Point[]{p1,p2,p3,p4};
        pts = validate(pts);

        this.pts = pts;
    }

    protected Point[] validate(Point[] points)
    {
        if (areDuplicates(points) || areCollinear(points))
        {
            throw new IllegalArgumentException("Points do not form a quadrilateral");
        }

        points = sortPointsCyclic(points);

        return points;
    }
    
    // Get Methods
    // return first endpoint
    public Point getP1()
    {
        return pts[0];
    }
    
    // return second endpoint
    public Point getP2()
    {
        return pts[1];
    }

    // return third endpoint
    public Point getP3()
    {
        return pts[2];
    }

    // return fourth endpoint
    public Point getP4()
    {
        return pts[3];
    }

    public abstract double getArea();

    public String toString()
    {
        return String.format("%s%n%s%s%n%s%s%n%s%s%n%s%s%n",
            "The four vertices of this quadrilateral are:", "first vertex : ", getP1(), 
                "second vertex : ", getP2(), "third vertex : ", getP3(), "fourth vertex : ", getP4());
    }
}





