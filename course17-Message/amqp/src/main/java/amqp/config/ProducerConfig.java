package amqp.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author justZero
 * @since 2019/2/2
 */
@Configuration
@ComponentScan("amqp.alerts")
@Import(SharedConfig.class)
public class ProducerConfig {

    @Value("${spittle.queueName}")
    private String queueName;
    @Value("${spittle.exchangeName}")
    private String exchangeName;

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory cf) {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(cf);
        template.setExchange(exchangeName);
        template.setRoutingKey(queueName);
        template.setDefaultReceiveQueue(queueName);
        return template;
    }

}
