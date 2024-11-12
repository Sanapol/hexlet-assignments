package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public int getFloorCount() {
        return floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    public String toString() {
        return getFloorCount() + " этажный коттедж площадью " + getArea() + " метров";
    }

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
