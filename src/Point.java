/**
 * Represents an immutable point in a two-dimensional Cartesian coordinate system.
 * 
 * <p>Part of the Quadrilateral Modeler - a Java library for creating and analyzing 
 * quadrilateral shapes with precise floating-point arithmetic.</p>
 * 
 * <p>This class is <b>immutable</b> and <b>thread-safe</b>. Once created,
 * coordinate values cannot be changed.</p>
 * 
 * <p><b>Equality semantics:</b> Two points are considered equal if their
 * coordinates differ by less than {@code 1e-9}, accommodating floating-point
 * precision errors common in geometric calculations.</p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>{@code
 * Point p1 = new Point(0, 0);
 * Point p2 = new Point(3, 4);
 * System.out.println(p1);  // Output: Point(0.0, 0.0)
 * 
 * // Using factory method
 * Point p3 = Point.of(5, 6);
 * 
 * // Equality with tolerance
 * Point p4 = new Point(0.0, 0.0);
 * Point p5 = new Point(1e-10, 1e-10);
 * assert p4.equals(p5); // true, within EPSILON
 * }</pre>
 * 
 * @author Marilyn Croffie
 * @version 1.0
 * @since 1.0
 */

public final class Point 
{
    private final double x; 
    private final double y; 

    /**
     * Constructs a {@code Point} with the specified coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new {@code Point} instance with the specified coordinates.
     * 
     * <p>This factory method is provided for fluent API usage and readability
     * in method chains or streams. Functionally equivalent to the constructor.</p>
     * 
     * <p><b>Example:</b></p>
     * <pre>{@code
     * List<Point> points = Stream.of(
     *     Point.of(0, 0),
     *     Point.of(3, 4)
     * ).collect(toList());
     * }</pre>
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return a new {@code Point} instance
     */
    public static Point of(double x, double y) 
    {
        return new Point(x, y);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate
     */
    public double getX()
    {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate
     */
    public double getY()
    {
        return y;
    }

    /** 
     * Tolerance for floating-point equality comparisons.
     * Set to 1e-9 to accommodate standard geometric precision while
     * avoiding false positives from cumulative rounding errors.
     */
    private static final double EPSILON = 1e-9;

    /**
     * Compares this point to another for equality.
     * 
     * <p>Two points are considered equal if their x and y coordinates differ by less than
     * {@code EPSILON = 1e-9}, to accommodate floating-point precision errors.
     * This ensures reliable behavior when performing geometric algorithms like distance checks,
     * shape validation, or transformations.</p>
     * 
     * <p><b>Implementation Note:</b> This method uses tolerance-based comparison rather than
     * exact equality ({@code ==}) to handle floating-point precision issues inherent in 
     * geometric calculations. The implementation is intentionally explicit to illustrate 
     * precision handling best practices.</p>
     * 
     * @param obj the object to compare with
     * @return {@code true} if both points are equal within tolerance; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (obj == null || obj.getClass()!= this.getClass())
            return false;
        
        Point other = (Point)obj;

        return Math.abs(this.x - other.x) < EPSILON && Math.abs(this.y - other.y) < EPSILON;
    }

    /**
     * Returns a hash code value for this point, consistent with {@link #equals(Object)}.
     *
     * <p><b>Implementation rationale:</b></p>
     * <ul>
     *   <li>Follows Java's standard hashCode contract</li>
     *   <li>Uses bitwise double representations to ensure equal coordinates hash identically</li>
     *   <li>Combines fields with prime multipliers (17, 31) to achieve uniform distribution</li>
     *   <li>Adheres to widely accepted Java conventions for hashing floating-point values</li>
     * </ul>
     * 
     * @return a hash code for this point
     */
    @Override
    public int hashCode() 
    {
        int result = 17;

        long bits = Double.doubleToLongBits(x);
        int hash = (int)(bits ^ (bits >>> 32));
        result = 31 * result + hash;
        
        bits = Double.doubleToLongBits(y);
        hash = (int)(bits ^ (bits >>> 32));
        result = 31 * result + hash;
        
        return result;
    }

    /** 
     * Returns a string representation in the form {@code Point(x, y)}
     * with coordinates rounded to 1 decimal place for readability.
     * 
     * @return formatted string representation
     */
    @Override
    public String toString()
    {
        return String.format("Point(%.1f, %.1f)", x, y);
    }
}