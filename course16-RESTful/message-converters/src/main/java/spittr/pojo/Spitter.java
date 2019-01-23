package spittr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Random;

@Data
@AllArgsConstructor
public class Spitter {

    private Long id;

    @NotBlank(message = "{username.notblank}")
    @Size(min = 5, max = 16, message = "{username.size}")
    private String username;

    @NotBlank(message = "{password.notblank}")
    @Size(min = 5, max = 25, message = "{password.size}")
    private String password;

    @NotBlank(message = "{firstname.notblank}")
    @Size(min = 1, max = 10, message = "{firstname.size}")
    private String firstName;

    @NotBlank(message = "{lastname.notblank}")
    @Size(min = 1, max = 10, message = "{lastname.size}")
    private String lastName;

    @Email(message = "{email.check}")
    @NotBlank(message = "{email.notblank}")
    private String email;

    private static final Random RANDOM = new Random();

    public Spitter() {
        this.id = Math.abs(RANDOM.nextLong());
    }

    public Spitter(String username, String password, String firstName,
                   String lastName, String email) {
        this(Math.abs(RANDOM.nextLong()), username, password,
                firstName, lastName, email);
    }

}
