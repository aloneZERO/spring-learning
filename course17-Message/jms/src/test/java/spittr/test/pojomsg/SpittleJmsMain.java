package spittr.test.pojomsg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import spittr.alerts.AlertService;
import spittr.config.PojoMsgConfig;
import spittr.domain.Spittle;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * 模拟发送通知
 *
 * @author justZero
 * @since 2019-1-31
 */
public class SpittleJmsMain {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(PojoMsgConfig.class);
        AlertService alertService = context.getBean(AlertService.class);

        while (true) {
            System.out.print("请输入通知内容: ");
            String message = in.nextLine();
            Spittle spittle = new Spittle(message);
            alertService.sendSpittleAlert(spittle);
            if ("#exit".equals(spittle.getMessage())) {
                System.out.println("-- 通知结束 --");
                return;
            }
        }
    }

}
