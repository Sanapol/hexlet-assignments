package exercise;

// BEGIN
public class MaxThread extends Thread {
    private int[] numbers;
    private int result;

    @Override
    public void run() {
        result = numbers[0];
        for (int i = 0; i < numbers.length - 1; i++) {
            if (result < numbers[i + 1]) {
                result = numbers[i + 1];
            }
        }
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public int getResult() {
        return result;
    }
}
// END
