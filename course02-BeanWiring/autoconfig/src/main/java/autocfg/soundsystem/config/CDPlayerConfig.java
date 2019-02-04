package autocfg.soundsystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 组件扫描 -> 自动装配
 * 自动扫描 autocfg.soundsystem 包下所有含有 Spring 相关注解的类，
 * 并在启动 Spring 上下文时对注解 Bean 进行实例化。
 * 如 @Component：标记该类为 Spring 的组件即 Bean。
 *
 * 自动装配 Bean 时，默认使用空参构造器；
 * 若仅有含参数的构造器，则要在构造方法上使用 @Autowired，
 * 同时要保证参数也是 Spring 管理的 Bean，这样参数才能被自动注入来完成自动装配。
 *
 * @author justZero
 * @since 2018/12/28
 */
@Configuration
@ComponentScan("autocfg.soundsystem")
public class CDPlayerConfig {

}
