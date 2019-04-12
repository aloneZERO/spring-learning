package contacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author justZero
 * @since 2019-4-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
}