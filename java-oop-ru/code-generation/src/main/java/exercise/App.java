package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path path, Car car) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(path.toFile(), car);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path path) {
        ObjectMapper mapper = new ObjectMapper();
        Car car = new Car();
        try {
            car = mapper.readValue(path.toFile(), Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return car;
    }
}
// END
