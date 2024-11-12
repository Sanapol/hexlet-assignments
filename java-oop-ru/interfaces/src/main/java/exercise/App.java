package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> homes, int count) {
        List<String> result = new ArrayList<>();

        if (homes.isEmpty()) {
            return result;
        }

        List<Home> sortedHomes = homes.stream()
                .sorted(Comparator.comparing(Home::getArea))
                .toList();

        for (int i = 0; i < count; i++) {
            result.add(sortedHomes.get(i).toString());
        }
        return result;
    }
}
// END
