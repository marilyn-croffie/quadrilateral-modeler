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

  public static boolean areParallel(Point v1, Point v2)
  {
    double cross = v1.getY() * v2.getX() - v2.getY() * v1.getX();
    
    return isZero(cross);
  }

  public static boolean areDuplicates(Point p1, Point p2)
  {
     return p1.equals(p2);
  }

  public static boolean areDuplicates(Point p1, Point p2, Point p3)
  {
     return p1.equals(p2) ||
            p2.equals(p3);
  }

  public static boolean areDuplicates(Point p1, Point p2, Point p3, Point p4)
  {
     return p1.equals(p2) ||
            p2.equals(p3) ||
            p3.equals(p4);
  }

  public static boolean arePerpendicular(Point v1, Point v2)
  {
    double dot = v1.getX() * v2.getX() + v1.getY() * v2.getY();

    return isZero(dot);
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
    double cross = Math.abs(lineVector.getX() * pointVector.getY() - lineVector.getY() * pointVector.getX());
    double lineLength = distance(p1, p2);
    
    return cross / lineLength;
  }

  private static Point[] sortPointsCyclic(Point[] points) 
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

  private static boolean isParallelOrder(Point[] points)
  {
    Point sideA = toVector(points[0],points[1]);
    Point sideB = toVector(points[2],points[3]);
    Point sideC = toVector(points[1],points[2]);
    Point sideD = toVector(points[3],points[0]);

    boolean pair1 = areParallel(sideA, sideB);
    boolean pair2 = areParallel(sideC, sideD);

    List<Point> pts = new ArrayList<>();

    if (pair1)
    {
      return true;
    }
    else if (pair2)
    {
      return false;
    }
    else
    {
      // No parallel sides
      throw new IllegalArgumentException("Points do not form a quadrilateral with parallel sides");
    }
  }

  public static Point[] reOrder(Point p1, Point p2, Point p3, Point p4)
  {
    Point[] pts = new Point[]{p1, p2, p3, p4};
    pts = sortPointsCyclic(pts);
    
    if (isParallelOrder(pts))
    {
      return pts;
    }
    else
    {
      return new Point[]{pts[1], pts[2], pts[3], pts[0]};
    }
  } 
}







