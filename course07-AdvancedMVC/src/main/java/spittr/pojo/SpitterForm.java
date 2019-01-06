package spittr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * @author justZero
 * @since 2019/1/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpitterForm {

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

    /**
     * 由SpitterForm生成Spitter实例
     * @param avatar 头像图片文件名（含后缀）
     * @return Spitter实例
     */
    public Spitter toSpitter(String avatar) {
        return new Spitter(username, password, firstName, lastName, email, avatar);
    }

}
