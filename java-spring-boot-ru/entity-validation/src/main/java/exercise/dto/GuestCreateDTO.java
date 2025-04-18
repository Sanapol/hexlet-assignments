package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {
    @NotEmpty
    private String name;
    @Email
    private String email;
    @Size(min = 11, max = 13)
    @Pattern(regexp = "^\\+\\d*")
    private String phoneNumber;
    @Size(min = 4, max = 4)
    private String clubCard;
    @FutureOrPresent
    private LocalDate cardValidUntil;
}
// END
