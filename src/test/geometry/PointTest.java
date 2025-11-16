package geometry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Point Tests")
class PointTest {
    
    @Test
    @DisplayName("Constructor should create point with correct coordinates")
    void testConstructor() {
        Point p = new Point(3.5, 4.2);
        assertEquals(3.5, p.getX(), 1e-9);
        assertEquals(4.2, p.getY(), 1e-9);
    }
    
    @Test
    @DisplayName("Factory method should create point")
    void testFactoryMethod() {
        Point p = Point.of(1.0, 2.0);
        assertEquals(1.0, p.getX());
        assertEquals(2.0, p.getY());
    }
    
    @Test
    @DisplayName("Points with identical coordinates should be equal")
    void testEqualsIdentical() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(1.0, 2.0);
        assertEquals(p1, p2);
    }
    
    @Test
    @DisplayName("Points within EPSILON should be equal")
    void testEqualsWithinEpsilon() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(1.0 + 1e-10, 2.0 + 1e-10);
        assertEquals(p1, p2);
    }
    
    @Test
    @DisplayName("Points outside EPSILON should not be equal")
    void testNotEqualsOutsideEpsilon() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(1.001, 2.0);
        assertNotEquals(p1, p2);
    }
    
    @Test
    @DisplayName("Point should equal itself")
    void testEqualsReflexive() {
        Point p = new Point(5.0, 6.0);
        assertEquals(p, p);
    }
    
    @Test
    @DisplayName("Point should not equal null")
    void testNotEqualsNull() {
        Point p = new Point(1.0, 2.0);
        assertNotEquals(p, null);
    }
    
    @Test
    @DisplayName("Point should not equal different type")
    void testNotEqualsDifferentType() {
        Point p = new Point(1.0, 2.0);
        assertNotEquals(p, "Not a point");
    }
    
    @Test
    @DisplayName("Equal points should have same hashCode")
    void testHashCodeConsistency() {
        Point p1 = new Point(3.0, 4.0);
        Point p2 = new Point(3.0, 4.0);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
    
    @Test
    @DisplayName("toString should format correctly")
    void testToString() {
        Point p = new Point(3.14159, 2.71828);
        String str = p.toString();
        assertTrue(str.contains("3.1"));
        assertTrue(str.contains("2.7"));
    }
}
