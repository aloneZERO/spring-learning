package spittr.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;
import java.text.SimpleDateFormat;

/**
 * 消息驱动 POJO 的作用：
 * 通过配置监听器来实现异步接收消息，
 * 而不是一直等待消息的到来。
 *
 * @author justZero
 * @since 2019/1/29
 */
@Configuration
@EnableJms
@ComponentScan("spittr.listener")
@PropertySource("classpath:jms.properties")
public class JmsListenerConfig {

    @Value("${mq.brokerUrl}")
    private String brokerUrl;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setTrustAllPackages(true);
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        // 设置消息模型为：发布-订阅模型（Topics）
        // 默认为 false，即点对点模型（Queues）
        factory.setPubSubDomain(true);
        factory.setConcurrency("1-1");
        return factory;
    }

}
