package spittr.alerts;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Service;
import spittr.domain.Spittle;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

    private JmsOperations jmsOperations;

//    public void sendSpittleAlert(final Spittle spittle) {
//        jmsOperations.send(
//                (session)-> session.createObjectMessage(spittle) );
//    }

    @Override
    public void sendSpittleAlert(final Spittle spittle) {
        jmsOperations.convertAndSend(spittle);
    }

//    public Spittle retrieveSpittleAlert() {
//        try {
//            ObjectMessage message = (ObjectMessage) jmsOperations.receive();
//            return (Spittle) message.getObject();
//        } catch (JMSException e) {
//            throw JmsUtils.convertJmsAccessException(e);
//        }
//    }

    @Override
    public Spittle retrieveSpittleAlert() {
        return (Spittle) jmsOperations.receiveAndConvert();
    }

}
