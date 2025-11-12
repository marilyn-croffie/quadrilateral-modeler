public final class PointUtils
{
  private static final double EPSILON = 1e-9;
  
  private PointUtils(){} // private constructor to prevent instantiation

  // Check if three points are collinear
  public static boolean areCollinear(Point p1, Point p2, Point p3)
  {
    // Using slope formula: (y2 - y1)/(x2 - x1) == (y3 - y1)/(x3 - x1)
    // Avoid divide by zero by cross-multiplying
    double dx1 = p2.getX() - p1.getX();
    double dy1 = p2.getY() - p1.getY();
    double dx2 = p3.getX() - p1.getX();
    double dy2 = p3.getY() - p1.getY();

    return ((dy1 * dx2 - dy2 * dx1) < EPSILON);
  }

  // Check if four points are collinear (any three forming a line)
  public static boolean areAnyThreeCollinear(Point p1, Point p2, Point p3, Point p4) 
  {
        return areCollinear(p1, p2, p3) ||
               areCollinear(p1, p2, p4) ||
               areCollinear(p1, p3, p4) ||
               areCollinear(p2, p3, p4);
  }

  
}
