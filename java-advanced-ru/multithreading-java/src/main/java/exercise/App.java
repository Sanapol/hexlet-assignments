package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class   App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread maxThread = new MaxThread();
        MinThread minThread = new MinThread();

        maxThread.setNumbers(numbers);
        minThread.setNumbers(numbers);

        Thread threadMax = new Thread(maxThread);
        Thread threadMin = new Thread(minThread);

        threadMax.start();
        threadMin.start();
        try {
            threadMax.join();
            threadMin.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("min", minThread.getResult());
        result.put("max", maxThread.getResult());
        return result;
    }
    // END
}
