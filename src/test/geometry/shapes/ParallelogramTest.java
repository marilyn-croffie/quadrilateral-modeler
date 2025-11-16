package geometry.shapes;

import geometry.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Parallelogram Tests")
class ParallelogramTest {
    
    @Test
    @DisplayName("Should create valid parallelogram")
    void testValidParallelogram() {
        assertDoesNotThrow(() -> {
            new Parallelogram(
                new Point(0, 0),
                new Point(4, 0),
                new Point(1, 3),
                new Point(5, 3)
            );
        });
    }
    
    @Test
    @DisplayName("Should calculate correct area")
    void testParallelogramArea() {
        Parallelogram para = new Parallelogram(
            new Point(0, 0),
            new Point(4, 0),
            new Point(1, 3),
            new Point(5, 3)
        );
        // Area = base * height = 4 * 3 = 12
        assertEquals(12.0, para.getArea(), 1e-9);
    }
    
    @Test
    @DisplayName("Should reject shape with only one pair of parallel sides")
    void testRejectTrapezoid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Parallelogram(
                new Point(0, 0),
                new Point(4, 0),
                new Point(1, 3),
                new Point(3, 3)
            );
        });
    }
    
    @Test
    @DisplayName("Should accept rectangle (special parallelogram)")
    void testAcceptRectangle() {
        assertDoesNotThrow(() -> {
            new Parallelogram(
                new Point(0, 0),
                new Point(4, 0),
                new Point(4, 3),
                new Point(0, 3)
            );
        });
    }
}
