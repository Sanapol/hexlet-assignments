package exercise;

import java.util.ArrayList;
import java.util.Arrays;

class SafetyList {
    // BEGIN
    private int[] numbers = new int[0];

    public synchronized void add(int number) {
        int[] massive = new int[numbers.length + 1];
        for (int i = 0; i <= numbers.length - 1; i++) {
            massive[i] = numbers[i];
        }
        massive[massive.length - 1] = number;
        numbers = massive;
    }

    public int get(int count) {
        return numbers[count];
    }

    public int getSize() {
        return numbers.length;
    }
    // END
}
