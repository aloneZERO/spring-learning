package amqp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import amqp.alerts.AlertService;
import amqp.config.ProducerConfig;
import amqp.domain.Spittle;

import java.util.Scanner;

/**
 * @author justZero
 * @since 2019-2-2
 */
public class ProducerMain {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProducerConfig.class);

        AlertService alertService = context.getBean(AlertService.class);

        while (true) {
            System.out.print("发送新通知：");
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
