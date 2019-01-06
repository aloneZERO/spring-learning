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

    @NotNull(message = "ID不能为空")
    private Long id;

    @NotNull(message = "用户名不能为空")
    @Size(min = 5, max = 16, message = "用户名长度应5到16个字符")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 5, max = 25, message = "密码长度应5到25个字符")
    private String password;

    @NotNull(message = "姓氏不能为空")
    @Size(min = 1, max = 10, message = "姓氏长度应1到10个字符")
    private String firstName;

    @NotNull(message = "名字不能为空")
    @Size(min = 1, max = 10, message = "名字长度应1到10个字符")
    private String lastName;

    @NotNull(message = "用户名不能为空")
    @Email(message = "非法邮箱地址")
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
