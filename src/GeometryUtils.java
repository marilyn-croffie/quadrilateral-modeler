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

  public static arePerpendicular(Point v1, Point v2)
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
}



