package aop.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author justZero
 * @since 2018/12/31
 */
@Configuration
@ComponentScan("aop.demo")
@EnableAspectJAutoProxy
public class ConcertConfig {}
