package amqp.receiver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.stereotype.Component;
import amqp.domain.Spittle;

@Slf4j
@Component
@AllArgsConstructor
public class SpittleAlertReceiver {

    private static final String LISTENER_CONTAINER_ID = "spittleAlert";

    private RabbitListenerEndpointRegistry listenerRegistry;

    @RabbitListener(id = LISTENER_CONTAINER_ID,
            queues = "${spittle.queueName}")
    public void handleSpittleAlert(Spittle spittle) {
        log.debug("{}", spittle);
        if ("#exit".equals(spittle.getMessage())) {
            listenerRegistry.stop();
            return;
        }
        System.out.println("收到新通知：" + spittle.getMessage());
    }
}
