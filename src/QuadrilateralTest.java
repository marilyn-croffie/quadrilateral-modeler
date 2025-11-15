// Code authored by Marilyn Croffie
// Ex 9.8 : QuadrilateralTest Class
// Testing the Quadrilateral class
// Created 6/29/25

import geometry.Point;
import geometry.shapes.*;

public class QuadrilateralTest {
    // Test class for all shapes
    public static void main(String[] args) {

        // Example Trapezoid
        Trapezoid trapezoid = new Trapezoid(
            new Point(0, 0),
            new Point(4, 0),
            new Point(1, 3),
            new Point(3, 3)
        );
        System.out.printf("Trapezoid: %s\nArea: %.2f\n\n", trapezoid, trapezoid.getArea());

        // Example Parallelogram
        Parallelogram parallelogram = new Parallelogram(
            new Point(0, 0),
            new Point(4, 0),
            new Point(1, 3),
            new Point(5, 3)
        );
        System.out.printf("Parallelogram: %s\nArea: %.2f\n\n", parallelogram, parallelogram.getArea());

        // Example Rectangle
        Rectangle rectangle = new Rectangle(
            new Point(0, 0),
            new Point(4, 0),
            new Point(0, 3),
            new Point(4, 3)
        );
        System.out.printf("Rectangle: %s\nArea: %.2f\n\n", rectangle, rectangle.getArea());

        // Example Square
        Square square = new Square(
            new Point(0, 0),
            new Point(2, 0),
            new Point(0, 2),
            new Point(2, 2)
        );
        System.out.printf("Square: %s\nArea: %.2f\n", square, square.getArea());
    }

}
