package spittr.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.Lifecycle;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;
import spittr.domain.Spittle;

@Slf4j
@Component
@AllArgsConstructor
public class SpittleAlertListener {

    private JmsListenerEndpointRegistry listenerRegistry;

    @JmsListener(destination = "spittle.alert.topic")
    public void handleSpittleAlert(Spittle spittle) {
        log.debug("{}", spittle);
        if ("#exit".equals(spittle.getMessage())) {
            listenerRegistry.destroy();
            return;
        }
        System.out.println("收到新通知：" + spittle.getMessage());
    }
}
