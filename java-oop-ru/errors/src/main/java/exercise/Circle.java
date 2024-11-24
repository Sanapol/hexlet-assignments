package exercise;

// BEGIN
public class Circle {
    private Point point;
    private int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        double result = Math.PI * (radius * radius);

        try {
            if (radius < 0) {
                throw new NegativeRadiusException("Радиус не может быть отрицательным");
            }
        } catch (RuntimeException e) {
            throw new NegativeRadiusException("Радиус не может быть отрицательным");
        }
        return result;
    }
}
// END
