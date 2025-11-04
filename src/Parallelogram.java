// Code authored by Marilyn Croffie
// Ex 9.8 : Parallelogram Class
// Parallelogram class represents a four-sided shape, quadrilateral,
// with two pairs of parallel, congruent, opposite sides and angles
// This (sub)class inherits from the Trapezoid (super)class
// Created 6/28/25

import java.util.Arrays;
import java.util.List;

public class Parallelogram extends Trapezoid 
{
    public Parallelogram(Point p1, Point p2, Point p3, Point p4)
    {
        // Initialize using superclass constructor
        super(p1, p2, p3, p4);

        // further validate points by checking for congruence
        if (numParallelPairs(getParallelSegmentPoints()) != 2)
        {
            throw new IllegalArgumentException(
                "Points do not form a parallelogram");
        }
        else
        {
            reValidate(getParallelSegmentPoints());
        }
    }

    // Helper Method(s)
    // checks for congruence
    private void reValidate(List<Point> parallelSegmentPoints)
    {
        if (parallelSegmentPoints.size() < 8) 
        {
            throw new IllegalStateException(
                "Not enough segment points to validate congruence.");
        }
        
        double[] sides = computeSideLengths(parallelSegmentPoints);
        validateCongruentOpposites(sides);
    }

    protected final double[] computeSideLengths(List<Point> parallelSegmentPoints) 
    {
        return new double[] 
        {
            length(delta(parallelSegmentPoints.get(0), parallelSegmentPoints.get(1))), // base1
            length(delta(parallelSegmentPoints.get(2), parallelSegmentPoints.get(3))), // base2
            length(delta(parallelSegmentPoints.get(4), parallelSegmentPoints.get(5))), // side1
            length(delta(parallelSegmentPoints.get(6), parallelSegmentPoints.get(7)))  // side2
        };
    }

    private void validateCongruentOpposites(double[] sides) 
    {
        double base1 = sides[0], base2 = sides[1];
        double side1 = sides[2], side2 = sides[3];

        if (!(areEqual(base1, base2) && areEqual(side1, side2))) 
        {
            throw new IllegalArgumentException("Points do not form a parallelogram");
        }
    }

    @Override
    public double getArea()
    {
        if (getParallelSegmentPoints().size() < 4)
            throw new IllegalStateException(
                "Not enough segment points to compute trapezoid area.");

        List<Point> seg1 = Arrays.asList(getParallelSegmentPoints().get(0), getParallelSegmentPoints().get(1));
        List<Point> seg2 = Arrays.asList(getParallelSegmentPoints().get(2), getParallelSegmentPoints().get(3));

        double[] delta = delta(seg1.get(0), seg1.get(1));
        double base = length(delta);
        double height = perpendicularDistanceBetweenTwoLines(seg1.get(0), seg1.get(1), seg2.get(0));

        double area = base*height;

        return area;
    }
}