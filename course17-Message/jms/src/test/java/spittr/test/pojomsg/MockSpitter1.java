package spittr.test.pojomsg;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spittr.config.JmsListenerConfig;

/**
 * 模拟用户接收通知
 *
 * 因为 Spring 在配置应用上文的过程中创建了 JMS 监听器，
 * 所以程序会一直运行
 *
 * @author justZero
 * @since 2019-1-31
 */
public class MockSpitter1 {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(JmsListenerConfig.class);
    }

}
