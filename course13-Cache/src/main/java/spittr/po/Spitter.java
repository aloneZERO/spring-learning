package spittr.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spitter implements Serializable {

    private static final long serialVersionUID = 5123465878809180505L;

    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private boolean updateByEmail;

}
