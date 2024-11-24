package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    public String massage;

    public NegativeRadiusException(String massage) {
        this.massage = massage;
    }

    public static void exception(Circle circle) throws Exception{
        if(circle.getRadius() < 0) {
            System.out.println("Не удалось посчитать площадь");
        } else {
            System.out.println(Math.round(circle.getSquare()));
        }
    }
}
// END
