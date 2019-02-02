package amqp.alerts;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import amqp.domain.Spittle;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

    private AmqpTemplate rabbit;

    public void sendSpittleAlert(Spittle spittle) {
        rabbit.convertAndSend(spittle);
    }

}
