package amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;

/**
 * Advanced Message Queuing Protocol
 * 与 JMS 相比 AMQP 一个明显的优势：
 * JMS 仅是 API 的规范，而 AMQP 的线路层协议对消息格式也进行了规范。
 * 也就是说不同的 JMS 实现，消息可能不通用，而 AMQP 完全可以跨语言和
 * 平台使用。
 *
 * @author justZero
 * @since 2019/2/2
 */
@Configuration
@PropertySource("classpath:amqp.properties")
public class SharedConfig {

    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.port}")
    private Integer port;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;

    @Value("${spittle.queueName}")
    private String queueName;
    @Value("${spittle.queueName}")
    private String routingKey;
    @Value("${spittle.exchangeName}")
    private String exchangeName;

    @Bean
    public Binding spittleAlertBinding() {
        return BindingBuilder.bind(spittleAlertQueue())
                .to(spittleAlertExchange())
                .with(routingKey).noargs();
    }

    @Bean
    public Exchange spittleAlertExchange() {
        return new FanoutExchange(exchangeName, true, false);
    }

    @Bean
    public Queue spittleAlertQueue() {
        return new Queue(queueName);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory =
                new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory cf) {
        return new RabbitAdmin(cf);
    }

}
