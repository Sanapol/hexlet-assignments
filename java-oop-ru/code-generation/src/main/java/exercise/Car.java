package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

// BEGIN
@NoArgsConstructor
@AllArgsConstructor
@Getter
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(mapper);
        return mapper.toString();
    }

    public static Car deserialize(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Car car = new Car();
        try {
            car = mapper.readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return car;
    }
    // END
}
