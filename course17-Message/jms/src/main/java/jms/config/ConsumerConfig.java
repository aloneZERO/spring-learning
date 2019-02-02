package jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * 消息驱动 POJO：
 * 默认情况下，虽然发消息是异步，但接收消息却是同步阻塞。
 * 所以通过配置监听器来实现异步接收消息，而不是一直等待消息的到来。
 *
 * @author justZero
 * @since 2019/1/29
 */
@Configuration
@EnableJms
@ComponentScan("jms.listener")
@Import(jms.config.SharedConfig.class)
public class ConsumerConfig {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            ConnectionFactory cf) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(cf);

        // 设置消息模型为：发布-订阅模型（Topics）
        // 默认为 false，即点对点模型（Queues）
        factory.setPubSubDomain(true);

        factory.setConcurrency("1-1");
        return factory;
    }

}
