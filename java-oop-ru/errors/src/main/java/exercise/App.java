package exercise;

// BEGIN
public class App {
    private Point point;
    private int square;

    public App(Point point, int square) {
        this.point = point;
        this.square = square;
    }

    public static double printSquare(Circle circle) throws NegativeRadiusException {
        var result = Math.PI * (circle.getRadius() * circle.getRadius());
        try {
            NegativeRadiusException.exception(circle);
        } catch (Exception e) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
        return result;
    }
}
// END
