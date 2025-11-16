package geometry.shapes;

import geometry.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Integration Tests")
class QuadrilateralIntegrationTest {
    
    @Test
    @DisplayName("Inheritance chain should work correctly")
    void testInheritanceChain() {
        Square square = new Square(
            new Point(0, 0),
            new Point(2, 0),
            new Point(2, 2),
            new Point(0, 2)
        );
        
        // Square is also a Rectangle, Parallelogram, Trapezoid, and Quadrilateral
        assertTrue(square instanceof Rectangle);
        assertTrue(square instanceof Parallelogram);
        assertTrue(square instanceof Trapezoid);
        assertTrue(square instanceof Quadrilateral);
    }
    
    @Test
    @DisplayName("All shapes should have toString")
    void testToString() {
        Trapezoid trap = new Trapezoid(
            new Point(0, 0),
            new Point(4, 0),
            new Point(1, 3),
            new Point(3, 3)
        );
        
        String str = trap.toString();
        assertNotNull(str);
        assertTrue(str.contains("vertex"));
    }
    
    @Test
    @DisplayName("Area calculations should be consistent across hierarchy")
    void testAreaConsistency() {
        Point[] points = {
            new Point(0, 0),
            new Point(4, 0),
            new Point(4, 3),
            new Point(0, 3)
        };
        
        Rectangle rect = new Rectangle(points[0], points[1], points[2], points[3]);
        Parallelogram para = new Parallelogram(points[0], points[1], points[2], points[3]);
        
        assertEquals(rect.getArea(), para.getArea(), 1e-9);
    }
    
    @Test
    @DisplayName("Should handle edge case: very small shapes")
    void testVerySmallShape() {
        assertDoesNotThrow(() -> {
            new Square(
                new Point(0, 0),
                new Point(0.001, 0),
                new Point(0.001, 0.001),
                new Point(0, 0.001)
            );
        });
    }
    
    @Test
    @DisplayName("Should handle edge case: large coordinates")
    void testLargeCoordinates() {
        assertDoesNotThrow(() -> {
            new Square(
                new Point(1000000, 1000000),
                new Point(1000002, 1000000),
                new Point(1000002, 1000002),
                new Point(1000000, 1000002)
            );
        });
    }
}
