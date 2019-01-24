package spittr.config;

import com.icegreen.greenmail.spring.GreenMailBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Arrays;

/**
 * 配置 GreenMail
 *
 * @author justZero
 * @since 2019-1-24
 */
@Configuration
@ComponentScan("spittr")
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Value("${mailserver.host}")
    private String host;
    @Value("${mailserver.port}")
    private Integer port;
    @Value("${mailserver.username}")
    private String username;
    @Value("${mailserver.password}")
    private String password;

    @Bean
    public GreenMailBean greenMail() {
        GreenMailBean greenMailBean = new GreenMailBean();
        greenMailBean.setUsers(Arrays
                .asList("app:letmein01@spitter.com"));
        return greenMailBean;
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }

}
