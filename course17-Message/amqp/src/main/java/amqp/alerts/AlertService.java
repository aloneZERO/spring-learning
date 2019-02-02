package amqp.alerts;

import amqp.domain.Spittle;

public interface AlertService {

    void sendSpittleAlert(Spittle spittle);

}
