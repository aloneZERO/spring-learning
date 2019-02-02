package jms.test.pojomsg;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import jms.config.ConsumerConfig;

/**
 * 模拟用户接收通知
 *
 * @author justZero
 * @since 2019-1-31
 */
public class MockSpitter1 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConsumerConfig.class);
        System.out.println("等待新通知...");

        JmsListenerEndpointRegistry registry =
                context.getBean(JmsListenerEndpointRegistry.class);
        while (registry.isRunning());
        context.close();
    }

}
