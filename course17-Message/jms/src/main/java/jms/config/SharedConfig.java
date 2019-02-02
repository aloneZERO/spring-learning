package jms.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.jms.ConnectionFactory;

/**
 * 公共配置
 *
 * @author justZero
 * @since 2019/2/2
 */
@Configuration
@PropertySource("classpath:jms.properties")
public class SharedConfig {

    @Value("${mq.brokerUrl}")
    private String brokerUrl;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setTrustAllPackages(true); // 仅开发环境使用
        return factory;
    }

}
