// Code authored by Marilyn Croffie
// Ex 9.8 : Quadrilateral Class
// Quadrilateral class represents any shape with four sides
// This is the superclass which other (sub)classes inherit from
// Created 6/27/25

import GeometryUtils;

public abstract class Quadrilateral {

    private Point p1, p2, p3, p4;

    public Quadrilateral(Point p1, Point p2, Point p3, Point p4)
    {
        Point[] pts = new Point[]{p1,p2,p3,p4};
        pts = validator(pts);

        p1 = pts[0];
        p2 = pts[1];
        p3 = pts[2];
        p4 = pts[3];
    }

    // Helper Method(s)
    // validates point by checking for:
    // i. duplicates, and
    // ii. collinearity of three points
    protected Point[] validator(Point[] points)
    {
        if (areDuplicates(points) || areCollinear(points))
        {
            throw new IllegalArgumentException("Points do not form a quadrilateral");
        }

        points = sortPointsCyclic(points);

        if (!isParallelOrder(points))
        {
            points = Point[]{points[1], points[2], points[3], points[0])};
            if (!isParallelOrder(points))
            {
                throw new IllegalArgumentException("Points do not form a quadrilateral with parallel sides");
            }
        }

        return points;
    }
    
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

