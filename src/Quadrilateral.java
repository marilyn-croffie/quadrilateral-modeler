// Code authored by Marilyn Croffie
// Ex 9.8 : Quadrilateral Class
// Quadrilateral class represents any shape with four sides
// This is the superclass which other (sub)classes inherit from
// Created 6/27/25

public abstract class Quadrilateral {

    private Point p1, p2, p3, p4;

    public Quadrilateral(Point p1, Point p2, Point p3, Point p4)
    {
        this.p1 = p1;
        validateSecondPoint(p1, p2);
        this.p2 = p2;
        validateThirdPoint(p1, p2, p3);
        this.p3 = p3;
        validateFourthPoint(p1, p2, p3, p4);
        this.p4 = p4;
    }

    // Helper Method(s)
    // validates point by checking for:
    // i. duplicates, and
    // ii. collinearity of three points
    private void validateSecondPoint(Point p1, Point p)
    {
        if (samePoint(p1, p)) 
            throw new IllegalArgumentException(
                "Cannot use the same point twice");
    }

    private void validateThirdPoint(Point p1, Point p2, Point p)
    {
        if ((samePoint(p1, p)) || (samePoint(p2, p)))
            throw new IllegalArgumentException(
                "Cannot use the same point twice");
        if (formsStraightLine(p1, p2, p))
            throw new IllegalArgumentException(
                "Point cannot form a straight line");
    }

    private void validateFourthPoint(Point p1, Point p2, Point p3, Point p)
    {
        if ((samePoint(p1, p)) || (samePoint(p2, p)) || (samePoint(p3, p)))
            throw new IllegalArgumentException(
                "Cannot use the same point twice");
        if (formsStraightLine(p1, p2, p3, p))
            throw new IllegalArgumentException(
                "Point cannot form a straight line");
    }

    // checks if it's a duplicate point
    private boolean samePoint(Point p1, Point p2)
    {
        return (p2.equals(p1));
    }

    // checks if point is colinear with the two other points
    private boolean formsStraightLine(Point p1, Point p2, Point p)
    {
        // cases
        boolean p1p2pHaveSameX = areEqual(p1.getX(), p2.getX(), p.getX());
        boolean p1p2pHaveSameY = areEqual(p1.getY(), p2.getY(), p.getY());

        return (p1p2pHaveSameX || p1p2pHaveSameY); 
    }

    // checks if point is colinear with any two of the three other points
    private boolean formsStraightLine(Point p1, Point p2, Point p3, Point p)
    {
        // cases
        boolean p1p2pHaveSameX = areEqual(p1.getX(), p2.getX(), p.getX());
        boolean p2p3pHaveSameX = areEqual(p2.getX(), p3.getX(), p.getX());
        boolean p1p3pHaveSameX = areEqual(p1.getX(), p3.getX(), p.getX());
        boolean p1p2pHaveSameY = areEqual(p1.getY(), p2.getY(), p.getY());
        boolean p2p3pHaveSameY = areEqual(p2.getY(), p3.getY(), p.getY());
        boolean p1p3pHaveSameY = areEqual(p1.getY(), p3.getY(), p.getY());

        return (p1p2pHaveSameX || p1p2pHaveSameY || p2p3pHaveSameX || p2p3pHaveSameY || 
            p1p3pHaveSameX || p1p3pHaveSameY);
    }

    // checks if three points have the same x or y coordinates
    private boolean areEqual(double a, double b, double c)
    {
        return ((a == b) && (b == c));
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

    // Set Method(s)
    // reset all endpoints with points that have 
    // already been validated
    protected final void setPoints(Point p1, Point p2, Point p3, Point p4)
    {
        this.p1 = p1;
        validateSecondPoint(p1, p2);
        this.p2 = p2;
        validateThirdPoint(p1, p2, p3);
        this.p3 = p3;
        validateFourthPoint(p1, p2, p3, p4);
        this.p4 = p4;  
    }

    public abstract double getArea();

    public String toString()
    {
        return String.format("%s%n%s%s%n%s%s%n%s%s%n%s%s%n",
            "The four vertices of this quadrilateral are:", "first vertex : ", p1, 
                "second vertex : ", p2, "third vertex : ", p3, "fourth vertex : ", p4);
    }
}