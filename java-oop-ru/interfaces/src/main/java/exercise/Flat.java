package exercise;

// BEGIN
public class Flat implements Home{
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }

    @Override
    public int compareTo(Home home) {
        int result = 0;

        if (this.getArea() > home.getArea()) {
            result = 1;
        } else if (this.getArea() < home.getArea()) {
            result = -1;
        }
        return result;
    }
}
// END
