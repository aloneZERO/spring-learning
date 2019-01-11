package spittr.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author justZero
 * @since 2019/1/7
 */
public class PasswordEncoderTest {

    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPasswd = "hello123456";
        System.out.println(passwordEncoder.encode(rawPasswd));
    }

}
