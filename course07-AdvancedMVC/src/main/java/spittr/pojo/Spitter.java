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
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;

    public Spitter() {
        this.id = new Random().nextLong();
    }

    public Spitter(String username, String password, String firstName,
                   String lastName, String email, String avatar) {
        this(new Random().nextLong(), username, password,
                firstName, lastName, email, avatar);
    }

}
