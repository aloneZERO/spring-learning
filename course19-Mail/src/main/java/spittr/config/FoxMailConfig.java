package spittr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Properties;

/**
 * 使用第三方的邮件服务
 * 这里选用 Foxmail
 *
 * @author justZero
 * @since 2019/1/24
 */
@Configuration
@ComponentScan("spittr")
@PropertySource("classpath:mail.properties")
public class FoxMailConfig {

    @Value("${foxmail.host}")
    private String host;
    @Value("${foxmail.port}")
    private Integer port;
    @Value("${foxmail.username}")
    private String username;
    @Value("${foxmail.password}")
    private String password;

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // 使用 TLS
        props.put("mail.debug", "true"); // DEBUG
        return mailSender;
    }

    @Bean
    public ISpringTemplateEngine emailTemplateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(mailTemplateResolver());
        return engine;
    }
    @Bean
    public ITemplateResolver mailTemplateResolver() {
        ClassLoaderTemplateResolver resolver =
                new ClassLoaderTemplateResolver();
        resolver.setPrefix("/mail/");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(1); // 当配置了多个模板解析器时，需要设置不同的优先级（从1开始）
        return resolver;
    }
}
