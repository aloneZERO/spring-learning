package demo.config;

import demo.GreetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author justZero
 * @since 2019/1/5
 */
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(greetHandler(), "/ws/greet")
                // 启用SockJS：当WebSocket不被支持时，SockJS会自动选用最优的代替方案
                .withSockJS();
    }

    @Bean
    public GreetHandler greetHandler() {
        return new GreetHandler();
    }
}
