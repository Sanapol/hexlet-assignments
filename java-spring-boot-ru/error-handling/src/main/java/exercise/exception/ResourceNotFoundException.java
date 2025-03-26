package exercise.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

// BEGIN
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String ex) {
        super(ex);
    }
}
// END
