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

        // further validate points by checking for the number of parallel sides
        Point[] points = new Point[]{getPoint1(), getPoint2(), getPoint3(), getPoint4()};
        List<Point> vertices = new ArrayList<Point>(Arrays.asList(points));

        this.slopes = segmentSlopes(vertices);

        this.parallelPairCounts = countParallelPairs(slopes);

        this.parallelSegmentPoints = getParallelPairs(slopes, parallelPairCounts);

        if(numParallelPairs(parallelSegmentPoints) == 0)
            throw new IllegalArgumentException(
                "Points do not form a trapezoid");
    }

    // Helper Method(s)
    // returns lists of line segments with the same slope
    protected final HashMap<Double, List<List<Point>>> segmentSlopes(List<Point> vertices)
    {
        HashMap<Double, List<List<Point>>> slopes = new HashMap<Double, List<List<Point>>>();
            
        for (int i = 0; i < vertices.size(); i++)
        {
            for (int j = i + 1; j < vertices.size(); j++) 
            {
                Point p1 = vertices.get(i);
                Point p2 = vertices.get(j);
                Double newSlope = slope(p1, p2);

                boolean matched = false;

                for (Double existingSlope : slopes.keySet()) {
                    if (areEqual(existingSlope, newSlope)) 
                    {
                        slopes.get(existingSlope).add(Arrays.asList(p1, p2));
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    List<List<Point>> pairs = new ArrayList<>();
                    pairs.add(Arrays.asList(p1, p2));
                    slopes.put(newSlope, pairs);
                }
            }
        }

        return slopes;
    }

    // returns the number of parallel pairs per slope
    protected final HashMap<Double, Integer> countParallelPairs(HashMap<Double, List<List<Point>>> slopes)
    {
        HashMap<Double, Integer> pairCounts = new HashMap<>();

        for(Double key : slopes.keySet())
        {
            int pairCount = slopes.get(key).size()/2;

            if (pairCount > 0)
            {
                pairCounts.put(key, pairCount);
            }
        }

        return pairCounts;
    }

    // returns a list of parallel line segments in order
    protected final List<Point> getParallelPairs(HashMap<Double, List<List<Point>>> slopes, HashMap<Double, Integer> pairCounts)
    {
        List<Point> result = new ArrayList<>();

        for (Double slope : pairCounts.keySet()) 
        {
            int count = pairCounts.get(slope);
            List<List<Point>> segments = slopes.get(slope);

            // Safety check: only loop up to available segments
            int limit = Math.min(count * 2, segments.size());

            for (int i = 0; i < limit; i++) 
            {
                List<Point> segment = segments.get(i);
                // Add both points of the segment
                result.add(segment.get(0));
                result.add(segment.get(1));
            }
        }

        return result;
    }

    // returns the number of parallel pairs
    protected final int numParallelPairs(List<Point> segmentPoints) 
    {
        // Each parallel segment pair consists of 4 points (2 points per segment × 2 segments)
        return segmentPoints.size() / 4;
    }

    // checks if two slopes are equal within a tolerance
    boolean areEqual(Double s1, Double s2) 
    {
        if (s1.isInfinite() && s2.isInfinite()) 
            return true;

        return Math.abs(s1 - s2) < 1e-9;
    }

    // Getters
    protected HashMap<Double, List<List<Point>>> getSlopes() 
    {
        return slopes;
    }

    protected HashMap<Double, Integer> getParallelPairCounts() 
    {
        return parallelPairCounts;
    }

    protected List<Point> getParallelSegmentPoints() 
    {
        return parallelSegmentPoints;
    }

    // returns the rise and run of a line segment
    protected final double[] delta(Point p1, Point p2)
    {
        double run = p2.getX() - p1.getX();
        double rise = p2.getY() - p1.getY();

        return new double[]{run, rise};
    }

    // returns the length of a line segment
    protected final double length(double[] delta)
    {
        double xSquare = Math.pow(delta[0],2);
        double ySquare = Math.pow(delta[1],2);

        return Math.sqrt(xSquare + ySquare);
    }

    // returns the intercept of a line segment
    protected final double intercept(double slope, Point p)
    {
        double intercept = p.getY() - (slope * p.getX());

        return intercept;
    }

    // returns the slope of a line segment
    protected final Double slope(Point p1, Point p2)
    {
        double[] diff = delta(p1,p2);
        Double run = diff[0];
        Double rise = diff[1];

        if (run == 0)
        {
            return Double.POSITIVE_INFINITY;
        }
        else
        {
           return rise/run;
        }
    }

    // returns the height of a quadrilateral
    protected final double perpendicularDistanceBetweenTwoLines(Point p1, Point p2, Point p3)
    {
        double slope = slope(p1, p2);

        if (Double.isInfinite(slope))
        {
            return abs(p3.getX() - p1.getX());
        }
        else
        {
            double intercept1 = intercept(slope, p1);
            double intercept2 = intercept(slope, p3);
            double numerator = abs(intercept2 - intercept1);
            double denominator = sqrt(1 + Math.pow(slope, 2));
            
            return numerator/denominator;
        }
    }

    @Override
    // returns the area of a trapezoid
    public double getArea()
    {
        if (getParallelSegmentPoints().size() < 4)
            throw new IllegalStateException(
                "Not enough segment points to compute trapezoid area.");
        
        List<Point> seg1 = Arrays.asList(parallelSegmentPoints.get(0), parallelSegmentPoints.get(1));
        List<Point> seg2 = Arrays.asList(parallelSegmentPoints.get(2), parallelSegmentPoints.get(3));

        double[] delta1 = delta(seg1.get(0), seg1.get(1));
        double[] delta2 = delta(seg2.get(0), seg2.get(1));

        double base1 = length(delta1);
        double base2 = length(delta2);

        double height = perpendicularDistanceBetweenTwoLines(seg1.get(0), seg1.get(1), seg2.get(0));

        double area = ((base1 + base2)/2) * height;
       
        return area;
    }

}
