package geometry.shapes;

import geometry.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Trapezoid Tests")
class TrapezoidTest {
    
    @Test
    @DisplayName("Should create valid trapezoid")
    void testValidTrapezoid() {
        assertDoesNotThrow(() -> {
            new Trapezoid(
                new Point(0, 0),
                new Point(4, 0),
                new Point(1, 3),
                new Point(3, 3)
            );
        });
    }
    
    @Test
    @DisplayName("Should calculate correct area")
    void testTrapezoidArea() {
        Trapezoid trap = new Trapezoid(
            new Point(0, 0),
            new Point(4, 0),
            new Point(1, 3),
            new Point(3, 3)
        );
        // Area = (b1 + b2) / 2 * h = (4 + 2) / 2 * 3 = 9
        assertEquals(9.0, trap.getArea(), 1e-9);
    }
    
    @Test
    @DisplayName("Should reject collinear points")
    void testRejectCollinear() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Trapezoid(
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0)
            );
        });
    }
    
    @Test
    @DisplayName("Should reject duplicate points")
    void testRejectDuplicates() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Trapezoid(
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 0),
                new Point(0, 1)
            );
        });
    }
    
    @Test
    @DisplayName("Should reject shape without parallel sides")
    void testRejectNonParallel() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Trapezoid(
                new Point(0, 0),
                new Point(2, 1),
                new Point(3, 3),
                new Point(1, 2)
            );
        });
    }
    
    @Test
    @DisplayName("Should handle points in any order")
    void testPointOrdering() {
        Trapezoid trap1 = new Trapezoid(
            new Point(0, 0),
            new Point(4, 0),
            new Point(3, 3),
            new Point(1, 3)
        );
        
        Trapezoid trap2 = new Trapezoid(
            new Point(3, 3),
            new Point(1, 3),
            new Point(0, 0),
            new Point(4, 0)
        );
        
        // Both should have same area regardless of input order
        assertEquals(trap1.getArea(), trap2.getArea(), 1e-9);
    }
}
