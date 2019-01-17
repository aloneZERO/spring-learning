package spittr.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "spitter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spitter implements Serializable {

    private static final long serialVersionUID = 5123465878809180505L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column
    private boolean updateByEmail;

}
