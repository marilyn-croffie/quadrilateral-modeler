// Code authored by Marilyn Croffie
// Ex 9.8 : Rectangle Class
// Rectangle class represents a four-angled shape, quadrilateral,
// with two pairs of parallel, congruent, opposite angles and 
// four congruent (right) angles
// This (sub)class inherits from the Parallelogram (super)class
// Created 6/28/25

import java.util.Arrays;

public class Rectangle extends Parallelogram 
{
    public Rectangle(Point p1, Point p2, Point p3, Point p4)
    {
        // Initialize using superclass constructor
        super(p1, p2, p3, p4);

        // further validate points by checking for congruent angles
        reValidate();
    }

    // Helper Method(s)
    // checks for congruent angles
    private void reValidate()
    {
        if (!formsRightAngles())
            throw new IllegalArgumentException("Points do not form a rectangle");
    }

    boolean arePerpendicular(double m1, double m2) 
    {
        if (Double.isInfinite(m1) && m2 == 0) 
            return true;

        if (Double.isInfinite(m2) && m1 == 0) 
            return true;
        
        return Math.abs(m1 * m2 + 1) < 1e-6;
    }

    protected final boolean formsRightAngles()
    {
        Point[] pts = new Point[]{getPoint1(), getPoint2(), getPoint3(), getPoint4()};
        pts = sortPointsCyclic(pts); // reorders into polygonal loop

        for (int i = 0; i < 4; i++) 
        {
            Point p1 = pts[i];
            Point p2 = pts[(i + 1) % 4];
            Point p3 = pts[(i + 2) % 4];

            double m1 = slope(p1, p2);
            double m2 = slope(p2, p3);

            if (!arePerpendicular(m1, m2)) {
                return false;
            }
        }

        return true;
    }

    private Point[] sortPointsCyclic(Point[] points)
    {
        // Compute center point
        double sumX = 0, sumY = 0;

        for (Point p : points) {
            sumX += p.getX();
            sumY += p.getY();
        }

        final double centerX = sumX / points.length;
        final double centerY = sumY / points.length;

        // Sort by angle from center
        Arrays.sort(points, (a, b) -> 
        {
            double angleA = Math.atan2(a.getY() - centerY, a.getX() - centerX);
            double angleB = Math.atan2(b.getY() - centerY, b.getX() - centerX);
        
            return Double.compare(angleA, angleB);
        }
        );

    return points;
}

    @Override
    public double getArea()
    {
        Point[] pts = new Point[]{getPoint1(), getPoint2(), getPoint3(), getPoint4()};
        pts = sortPointsCyclic(pts); // reorder for consistent side pairing

        // Find two adjacent perpendicular sides
        double length = length(delta(pts[0], pts[1]));
        double breadth = length(delta(pts[1], pts[2]));
        
        double area = length*breadth;

        return area;
    }
}