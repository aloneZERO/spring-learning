package demo.config;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;

/**
 * STOMP: Simple Text Oriented Messaging Protocol
 *
 * @author justZero
 * @since 2019/1/5
 */
@EnableWebSocketMessageBroker
public class WebSocketStompConfig
        extends WebSocketMessageBrokerConfigurationSupport {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/greet").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic");

        // 启用STOMP代理中继功能：即将STOMP消息的处理委托给一个真正的消息代理
//        registry.enableStompBrokerRelay("/queue", "/topic");
//                .setRelayHost("rabbit.server")
//                .setRelayPort(62623)
//                .setClientLogin("username")
//                .setClientPasscode("passwd");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
