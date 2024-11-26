package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();

        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                String stringField = field.toString();
                String[] splitField = stringField.split("\\.");
                try {
                    Field clas = Address.class.getDeclaredField(splitField[splitField.length - 1]);
                    clas.setAccessible(true);
                    String value = (String) clas.get(address);
                    if (value == null) {
                        result.add(splitField[splitField.length - 1]);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                field.setAccessible(true);
            }
        }
        return result;
    }
}
// END
