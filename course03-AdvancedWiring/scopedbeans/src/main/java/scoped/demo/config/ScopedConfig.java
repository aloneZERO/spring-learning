package scoped.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Bean 作用域
 * 1. singleton 整个应用中，创建单一实例；（默认）
 * 2. prototype 每次注入或通过context获取，都会创建新实例；
 * 3. session Web应用中，每个会话创建单一实例；
 * 4. request Web应用中，每个请求创建单一实例；
 * 5. application web应用中，ServletContext 生命周期中创建单一实例；
 * 6. websocket 在 WebSocket 生命周期中创建单一实例
 *
 * @author justZero
 * @since 2018/12/30
 */
@Configuration
@ComponentScan("scoped.demo")
public class ScopedConfig {

}
