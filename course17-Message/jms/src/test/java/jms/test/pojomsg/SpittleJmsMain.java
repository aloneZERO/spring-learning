package jms.test.pojomsg;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import jms.alerts.AlertService;
import jms.config.ProducerConfig;
import jms.domain.Spittle;

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
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProducerConfig.class);
        AlertService alertService = context.getBean(AlertService.class);

        while (true) {
            System.out.print("发送新通知: ");
            String message = in.nextLine();
            Spittle spittle = new Spittle(message);
            alertService.sendSpittleAlert(spittle);
            if ("#exit".equals(spittle.getMessage())) {
                System.out.println("-- 通知完毕 --");
                break;
            }
        }
        context.close();
    }

}
