package spittr.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 配置 DelegatingFilterProxy
 * <p>
 * Spring Security 借助 Servlet Filter 提供安全功能，
 * 所以使用 Spring Security 需要通过继承 AbstractSecurityWebApplicationInitializer
 * 来注册 DelegatingFilterProxy —— 把 Filter 的处理逻辑委托给 Spring 应用上下文中定义的
 * 代理 Filter Bean
 */
public class SecurityWebInitializer
        extends AbstractSecurityWebApplicationInitializer {}
