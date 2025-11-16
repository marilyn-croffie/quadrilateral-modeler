package geometry.shapes;

import geometry.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Square Tests")
class SquareTest {
    
    @Test
    @DisplayName("Should create valid square")
    void testValidSquare() {
        assertDoesNotThrow(() -> {
            new Square(
                new Point(0, 0),
                new Point(2, 0),
                new Point(2, 2),
                new Point(0, 2)
            );
        });
    }
    
    @Test
    @DisplayName("Should calculate correct area")
    void testSquareArea() {
        Square square = new Square(
            new Point(0, 0),
            new Point(3, 0),
            new Point(3, 3),
            new Point(0, 3)
        );
        assertEquals(9.0, square.getArea(), 1e-9);
    }
    
    @Test
    @DisplayName("Should reject rectangle with unequal sides")
    void testRejectRectangle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Square(
                new Point(0, 0),
                new Point(4, 0),
                new Point(4, 3),
                new Point(0, 3)
            );
        });
    }
    
    @Test
    @DisplayName("Should handle rotated square")
    void testRotatedSquare() {
        assertDoesNotThrow(() -> {
            // Diamond orientation
            new Square(
                new Point(0, 0),
                new Point(2, 2),
                new Point(0, 4),
                new Point(-2, 2)
            );
        });
    }
    
    @Test
    @DisplayName("Should handle floating point precision")
    void testFloatingPointSquare() {
        assertDoesNotThrow(() -> {
            double side = Math.sqrt(2);
            new Square(
                new Point(0, 0),
                new Point(side, 0),
                new Point(side, side),
                new Point(0, side)
            );
        });
    }
}
