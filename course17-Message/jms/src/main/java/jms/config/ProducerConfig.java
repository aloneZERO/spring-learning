package jms.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;

/**
 * @author justZero
 * @since 2019/1/29
 */
@Configuration
@ComponentScan("jms.alerts")
@Import(jms.config.SharedConfig.class)
public class ProducerConfig {

    @Value("${spittle.topicName}")
    private String topicName;

    @Bean
    public Topic spittleTopic() {
        ActiveMQTopic topic = new ActiveMQTopic();
        topic.setPhysicalName(topicName);
        return topic;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory cf) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(cf);
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
