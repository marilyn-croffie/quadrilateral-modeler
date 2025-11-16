package geometry.shapes;

import geometry.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Rectangle Tests")
class RectangleTest {
    
    @Test
    @DisplayName("Should create valid rectangle")
    void testValidRectangle() {
        assertDoesNotThrow(() -> {
            new Rectangle(
                new Point(0, 0),
                new Point(4, 0),
                new Point(4, 3),
                new Point(0, 3)
            );
        });
    }
    
    @Test
    @DisplayName("Should calculate correct area")
    void testRectangleArea() {
        Rectangle rect = new Rectangle(
            new Point(0, 0),
            new Point(4, 0),
            new Point(4, 3),
            new Point(0, 3)
        );
        assertEquals(12.0, rect.getArea(), 1e-9);
    }
    
    @Test
    @DisplayName("Should reject parallelogram without right angles")
    void testRejectNonRightAngles() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(
                new Point(0, 0),
                new Point(4, 0),
                new Point(1, 3),
                new Point(5, 3)
            );
        });
    }
    
    @Test
    @DisplayName("Should accept square (special rectangle)")
    void testAcceptSquare() {
        assertDoesNotThrow(() -> {
            new Rectangle(
                new Point(0, 0),
                new Point(3, 0),
                new Point(3, 3),
                new Point(0, 3)
            );
        });
    }
    
    @Test
    @DisplayName("Should handle rotated rectangle")
    void testRotatedRectangle() {
        assertDoesNotThrow(() -> {
            new Rectangle(
                new Point(0, 0),
                new Point(3, 4),
                new Point(-1, 7),
                new Point(-4, 3)
            );
        });
    }
}
