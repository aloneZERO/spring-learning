package mail.demo.config;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 配置 GreenMail
 *
 * @author justZero
 * @since 2019-1-24
 */
@Configuration
@ComponentScan("mail.demo")
@PropertySource("classpath:mail.properties")
public class GreenMailConfig {

    @Value("${mailserver.host}")
    private String host;
    @Value("${mailserver.port}")
    private Integer port;
    @Value("${mailserver.username}")
    private String username;
    @Value("${mailserver.password}")
    private String password;

    @Bean
    public GreenMail greenMail() {
        // ServerSetupTest.SMTP 默认端口 3025
//        GreenMail greenMail = new GreenMail(ServerSetupTest.SMTP);

        GreenMail greenMail = new GreenMail(
                new ServerSetup(port, host, "smtp"));
        greenMail.setUser(username, password);
        return greenMail;
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
