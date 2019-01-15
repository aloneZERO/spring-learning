package demo.config;

import demo.GreetHandler;
import org.eclipse.jetty.websocket.api.WebSocketBehavior;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.server.WebSocketServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.jetty.JettyRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.ServletContext;

/**
 * @author justZero
 * @since 2019/1/5
 */
@Configuration
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

    // Spring MVC + Jetty 使用 WebSocket 需要添加如下配置
    // 详见 Spring 文档
    // https://docs.spring.io/spring/docs/5.1.4.RELEASE/spring-framework-reference/web.html#websocket-server-runtime-configuration
    @Bean
    public DefaultHandshakeHandler handshakeHandler(ServletContext context) {
        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
        policy.setInputBufferSize(8*1024); // 8KB
        policy.setIdleTimeout(233000); // 233s
        return new DefaultHandshakeHandler(
                new JettyRequestUpgradeStrategy(new WebSocketServerFactory(context, policy)));
    }
}
