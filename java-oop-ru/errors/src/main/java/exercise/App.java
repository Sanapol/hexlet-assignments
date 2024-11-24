package exercise;

// BEGIN
public class App {
    private Point point;
    private int square;

    public App(Point point, int square) {
        this.point = point;
        this.square = square;
    }

    public static void printSquare(Circle circle) throws NegativeRadiusException {
        try {
            System.out.println(Math.round(circle.getSquare()));
        } catch (Exception e) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
