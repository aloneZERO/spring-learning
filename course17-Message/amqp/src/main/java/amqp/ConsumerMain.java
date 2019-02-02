package amqp;

import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import amqp.config.ConsumerConfig;

/**
 * @author justZero
 * @since 2019-2-2
 */
public class ConsumerMain {

    public static void main(String[] args) {
        // 初始化 Spring 上下文的过程中会实例化消息监听器
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConsumerConfig.class);
        System.out.println("等待新通知中...");

        RabbitListenerEndpointRegistry registry =
                context.getBean(RabbitListenerEndpointRegistry.class);
        while (registry.isRunning());
        context.close();
    }

}
