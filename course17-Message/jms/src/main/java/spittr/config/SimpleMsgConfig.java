package spittr.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Collections;

/**
 * 异步消息两个主要的概念：消息代理（message broker）和目的地（destination）
 * 当一个应用发送消息时，会将消息交给一个消息代理。消息代理类似于邮局的工作。
 * 消息代理可以确保消息被投递到指定的目的地，同时解放发送者，使其能够继续进行其他的业务。
 *
 * 消息中间件相关博客：https://juejin.im/post/5b41fe36e51d45191252e79e
 *
 * @author justZero
 * @since 2019/1/29
 */
@Configuration
@PropertySource("classpath:jms.properties")
public class SimpleMsgConfig {

    @Value("${hello.queueName}")
    private String queueName;
    @Value("${mq.brokerUrl}")
    private String brokerUrl;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        return factory;
    }

    @Bean
    public Queue simpleMsgQueue() {
        ActiveMQQueue queue = new ActiveMQQueue();
        queue.setPhysicalName(queueName);
        return queue;
    }

    @Bean
    public JmsTemplate jmsTemplate(
            ConnectionFactory cf,
            Queue queue) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(cf);
        // 通过注入队列 Bean 来设置默认目的地类型及名称
        jmsTemplate.setDefaultDestination(queue);
        return jmsTemplate;
    }

}
