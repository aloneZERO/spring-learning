package spittr.test.simplemsg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsOperations;
import spittr.config.SimpleMsgConfig;

import java.util.Scanner;

/**
 * 发送者（即消息队列模型中的消费者）
 * 利用 Spring JmsTemplate 发送消息
 * 当输入"#exit"时退出
 *
 * @author justZero
 * @since 2019-1-31
 */
public class JmsSender {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SimpleMsgConfig.class);
        JmsOperations jms = context.getBean(JmsOperations.class);
        while (true) {
            System.out.print("发送者 >>> ");
            String message = in.nextLine();
            jms.convertAndSend(message);
            if ("#exit".equals(message)) {
                System.out.println("消息发送完毕");
                break;
            }
        }
    }

}
