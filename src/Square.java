// Code authored by Marilyn Croffie
// Ex 9.8 : Square Class
// Square class represents a four-sided shape, quadrilateral,
// with two pairs of parallel opposite sides and four congruent
// sides and (right) angles
// This (sub)class inherits from the Rectangle (super)class
// Created 6/28/25

public class Square extends Rectangle 
{
    public Square(Point p1, Point p2, Point p3, Point p4)
    {
        // Initialize using superclass constructor
        super(p1, p2, p3, p4);

        // further validate points by checking all sides are congruent
        reValidate();
    }

    private void reValidate()
    {
        double[] sides = computeSideLengths(getParallelSegmentPoints());

        double base1 = sides[0];
        double base2 = sides[1];
        double side1 = sides[2];
        double side2 = sides[3];

        if (!(areEqual(base1, base2) && areEqual(base1, side1) &&
            areEqual(base1, side2)))
            throw new IllegalArgumentException(
                "Points do not form a square");
    }
}