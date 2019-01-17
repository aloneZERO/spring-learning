package demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * STOMP: Simple Text Oriented Messaging Protocol
 *
 * @author justZero
 * @since 2019/1/5
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig
        implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/greet").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/queue", "/topic");

        // 启用 STOMP 代理中继功能：即将 STOMP 消息的处理委托给一个真正的消息代理
        // 测试时使用的是 ActiveMQ, 请配置相应的 stomp 链接地址
        registry.enableStompBrokerRelay("/queue", "/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
        registry.setApplicationDestinationPrefixes("/app");

    }
}
