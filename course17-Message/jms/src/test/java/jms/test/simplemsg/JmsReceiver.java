package jms.test.simplemsg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsOperations;
import jms.config.SimpleMsgConfig;

/**
 * 接收者（即消息队列模型中的消费者）
 * 利用 Spring JmsTemplate 接收消息
 * 当接收到"#exit"时，停止接收
 *
 * @author justZero
 * @since 2019/1/31
 */
public class JmsReceiver {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SimpleMsgConfig.class);
        JmsOperations jms = context.getBean(JmsOperations.class);
        while (true) {
            String message = (String) jms.receiveAndConvert();
            System.out.println("接收者 <<< " + message);
            if ("#exit".equals(message)) {
                System.out.println("消息接收完毕");
                break;
            }
        }
    }

}
