package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);
        List<String> result = new ArrayList<>();
        // BEGIN
        for (Method method : Address.class.getDeclaredMethods()) {


            if (method.isAnnotationPresent(Inspect.class)) {

                result.add("Method " + method.getName() + " returns a value of type "
                        + method.getReturnType().getSimpleName());
            }
        }
        System.out.println(result);
        // END
    }
}
