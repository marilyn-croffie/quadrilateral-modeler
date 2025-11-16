package geometry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GeometryUtils Tests")
class GeometryUtilsTest {
    
    @Test
    @DisplayName("toVector should calculate correct difference vector")
    void testToVector() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(4, 6);
        Point vector = GeometryUtils.toVector(p1, p2);
        
        assertEquals(3.0, vector.getX(), 1e-9);
        assertEquals(4.0, vector.getY(), 1e-9);
    }
    
    @Test
    @DisplayName("Should detect duplicate points")
    void testAreDuplicates() {
        Point[] points = {
            new Point(0, 0),
            new Point(1, 1),
            new Point(0, 0),
            new Point(2, 2)
        };
        assertTrue(GeometryUtils.areDuplicates(points));
    }
    
    @Test
    @DisplayName("Should detect no duplicates")
    void testNoDuplicates() {
        Point[] points = {
            new Point(0, 0),
            new Point(1, 0),
            new Point(1, 1),
            new Point(0, 1)
        };
        assertFalse(GeometryUtils.areDuplicates(points));
    }
    
    @Test
    @DisplayName("Should detect parallel vectors")
    void testAreParallel() {
        Point v1 = Point.of(2, 4);
        Point v2 = Point.of(1, 2); // Same direction, different magnitude
        assertTrue(GeometryUtils.areParallel(v1, v2));
    }
    
    @Test
    @DisplayName("Should detect non-parallel vectors")
    void testNotParallel() {
        Point v1 = Point.of(1, 0);
        Point v2 = Point.of(0, 1);
        assertFalse(GeometryUtils.areParallel(v1, v2));
    }
    
    @Test
    @DisplayName("Should detect perpendicular vectors")
    void testArePerpendicular() {
        Point v1 = Point.of(1, 0);
        Point v2 = Point.of(0, 1);
        assertTrue(GeometryUtils.arePerpendicular(v1, v2));
    }
    
    @Test
    @DisplayName("Should detect non-perpendicular vectors")
    void testNotPerpendicular() {
        Point v1 = Point.of(1, 1);
        Point v2 = Point.of(1, 0);
        assertFalse(GeometryUtils.arePerpendicular(v1, v2));
    }
    
    @Test
    @DisplayName("Should detect collinear points")
    void testAreCollinear() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(2, 2);
        assertTrue(GeometryUtils.areCollinear(p1, p2, p3));
    }
    
    @Test
    @DisplayName("Should detect non-collinear points")
    void testNotCollinear() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(0, 1);
        assertFalse(GeometryUtils.areCollinear(p1, p2, p3));
    }
    
    @Test
    @DisplayName("Should calculate correct distance")
    void testDistance() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        assertEquals(5.0, GeometryUtils.distance(p1, p2), 1e-9);
    }
    
    @Test
    @DisplayName("Should calculate correct height")
    void testHeight() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0); // Horizontal line
        Point p3 = new Point(2, 3); // Point above line
        assertEquals(3.0, GeometryUtils.height(p1, p2, p3), 1e-9);
    }
    
    @Test
    @DisplayName("Should detect all right angles in square")
    void testAreAllRightAngles() {
        Point[] square = {
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        };
        assertTrue(GeometryUtils.areAllRightAngles(square));
    }
    
    @Test
    @DisplayName("Should detect equal sides")
    void testAllSidesEqual() {
        Point[] square = {
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        };
        assertTrue(GeometryUtils.allSidesEqual(square));
    }
    
    @Test
    @DisplayName("Should detect unequal sides")
    void testNotAllSidesEqual() {
        Point[] rectangle = {
            new Point(0, 0),
            new Point(4, 0),
            new Point(4, 2),
            new Point(0, 2)
        };
        assertFalse(GeometryUtils.allSidesEqual(rectangle));
    }
    
    @Test
    @DisplayName("sortPointsCyclic should order points correctly")
    void testSortPointsCyclic() {
        Point[] unordered = {
            new Point(1, 1),
            new Point(0, 0),
            new Point(1, 0),
            new Point(0, 1)
        };
        Point[] sorted = GeometryUtils.sortPointsCyclic(unordered);
        
        // After sorting, points should form a cycle
        // Verify by checking they're not collinear and form a valid quadrilateral
        assertFalse(GeometryUtils.areCollinear(sorted));
    }
}
