package demo.controller;

import demo.Shout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * 处理STOMP消息
 *
 * @author justZero
 * @since 2019/1/5
 */
@Slf4j
@Controller
public class GreetController {

    @MessageMapping("/greet")
    public Shout handleShout(Shout incoming) {
        log.info("收到消息：{}", incoming.getMessage());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Shout outgoing = new Shout();
        outgoing.setMessage("干啥啊？");
        return outgoing;
    }

    @SubscribeMapping({"/greet"})
    public Shout handleSubscription() {
        log.info("处理订阅");

        Shout outgoing = new Shout();
        outgoing.setMessage("干啥啊？");
        return outgoing;
    }

}
