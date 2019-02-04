package scoped.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

/**
 * 购物车
 * 会话作用域最为合适
 *
 * 作用域代理——有待理解
 *
 * @author justZero
 * @since 2018/12/30
 */
@Component
//@Scope(value = WebApplicationContext.SCOPE_SESSION,
//        proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionScope
public class ShoppingCart {

}
