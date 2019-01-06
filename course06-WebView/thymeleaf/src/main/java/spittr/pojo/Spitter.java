package spittr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Random;

@Data
@AllArgsConstructor
public class Spitter {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 5, max = 16)
    private String username;

    @NotNull
    @Size(min = 5, max = 25)
    private String password;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Email
    private String email;

    public Spitter() {
        this.id = new Random().nextLong();
    }

    public Spitter(String username, String password, String
            firstName, String lastName, String email) {
        this(new Random().nextLong(), username, password,
                firstName, lastName, email);
    }

}
