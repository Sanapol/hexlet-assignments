package exercise;

import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Set<Entry<String, String>> entryMap = storage.toMap().entrySet();

        for (Entry<String, String> entry : entryMap) {
            storage.unset(entry.getKey());
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
