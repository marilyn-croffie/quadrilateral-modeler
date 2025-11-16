package geometry;

import java.util.Arrays;

public final class GeometryUtils
{
  private static final double EPSILON = 1e-9;
  
  private GeometryUtils(){} // private constructor to prevent instantiation

  public static Point toVector(Point p1, Point p2)
  {
    double dx = p2.getX() - p1.getX();
    double dy = p2.getY() - p1.getY();

    return Point.of(dx, dy);
  }

  private static boolean isZero(double value)
  {
    return Math.abs(value) < EPSILON;
  }

  private static double cross(Point v1, Point v2)
  {
    return v1.getY() * v2.getX() - v2.getY() * v1.getX();
  }

  private static double dot(Point v1, Point v2)
  {
    return v1.getX() * v2.getX() + v1.getY() * v2.getY();
  }
  
  public static boolean areDuplicates(Point[] points)
  {
     for (int i = 0; i < points.length; i++) 
     {
       for (int j = i + 1; j < points.length; j++) 
       {
         if (points[i].equals(points[j])) 
           return true;
       }
     }

    return false;
   }

  public static boolean areParallel(Point v1, Point v2)
  { 
    return isZero(cross(v1,v2));
  }

  public static boolean arePerpendicular(Point v1, Point v2)
  {
    return isZero(dot(v1,v2));
  }

  public static boolean areAllRightAngles(Point[] points)
  {
    Point l1 = toVector(points[0], points[1]);
    Point l2 = toVector(points[1], points[2]);
    Point l3 = toVector(points[2], points[3]);
    Point l4 = toVector(points[3], points[0]);
      
    return arePerpendicular(l1,l2) &&
           arePerpendicular(l2,l3) &&
           arePerpendicular(l3,l4) &&
           arePerpendicular(l4,l1);
  }

  public static boolean allSidesEqual(Point[] points)
  {
    double s1 = distance(points[0], points[1]);
    double s2 = distance(points[1], points[2]);
    double s3 = distance(points[2], points[3]);
    double s4 = distance(points[3], points[0]);

    return isZero(s1 - s2) &&
           isZero(s2 - s3) &&
           isZero(s3 - s4);
  }
    
  // Check if three points are collinear
  public static boolean areCollinear(Point p1, Point p2, Point p3)
  {
    Point v1 = toVector(p1, p2);
    Point v2 = toVector(p1, p3);

    return areParallel(v1, v2);
  }

  // Check if four points are collinear (any three forming a line)
  public static boolean areCollinear(Point p1, Point p2, Point p3, Point p4) 
  {
        return areCollinear(p1, p2, p3) ||
               areCollinear(p1, p2, p4) ||
               areCollinear(p1, p3, p4);
  }

  public static boolean areCollinear(Point[] points) 
  {
        return areCollinear(points[0], points[1], points[2], points[3]);
  }

  public static double distance(Point p1, Point p2)
  {
    Point v = toVector(p1, p2);

    return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
  }
  
  public static double height(Point p1, Point p2, Point p3)
  {
    Point lineVector =  toVector(p1,p2);
    Point pointVector =  toVector(p1,p3);
    
    // Cross product magnitude divided by line length
    double cross = Math.abs(cross(pointVector, lineVector));
    double lineLength = distance(p1, p2);
    
    return cross / lineLength;
  }

  public static Point[] sortPointsCyclic(Point[] points) 
  {
    // Compute center point
    double sumX = 0, sumY = 0;
    
    for (Point p : points) 
    {
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
    });

    return points;
  } 

  public static boolean isParallelOrder(Point[] points)
  {
    Point sideA = toVector(points[0],points[1]);
    Point sideB = toVector(points[2],points[3]);

    return areParallel(sideA, sideB);
  }
}
