package spittr.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import java.text.SimpleDateFormat;
import java.util.Collections;

/**
 * 消息驱动 POJO 的作用：
 * 通过配置监听器来实现异步接收消息，
 * 而不是一直等待消息的到来。
 *
 * @author justZero
 * @since 2019/1/29
 */
@Configuration
@ComponentScan("spittr.alerts")
@PropertySource("classpath:jms.properties")
public class PojoMsgConfig {

    @Value("${spittle.topicName}")
    private String topicName;
    @Value("${mq.brokerUrl}")
    private String brokerUrl;

    @Bean
    public Topic spittleTopic() {
        ActiveMQTopic topic = new ActiveMQTopic();
        topic.setPhysicalName(topicName);
        return topic;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setTrustAllPackages(true);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestination(spittleTopic());
        return jmsTemplate;
    }

//    @Bean
//    public MessageConverter jsonMsgConverter() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setSerializationInclusion(
//                JsonInclude.Include.NON_NULL);
//        objectMapper.setDateFormat(
//                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//
//        MappingJackson2MessageConverter converter =
//                new MappingJackson2MessageConverter();
//        converter.setObjectMapper(objectMapper);
//        converter.setEncoding("UTF-8");
//        return converter;
//    }

}
