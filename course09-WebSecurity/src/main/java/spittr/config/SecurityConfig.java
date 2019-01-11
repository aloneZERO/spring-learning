package spittr.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

import javax.sql.DataSource;
import java.io.*;

/**
 * @author justZero
 * @since 2019/1/7
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    // 配置 user-detail 服务
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication() // 基于内存的用户验证
                .withUser("user").password("233").roles("USER")
                    .and()
                .withUser("admin").password("passwd").roles("USER", "ADMIN");

//        auth
//            .jdbcAuthentication() // 基于数据库的用户验证
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                    "select username, password, true from Spitter where username=?")
//                .authoritiesByUsernameQuery(
//                    "select username, 'ROLE_USER' from Spitter where username=?")
//                .passwordEncoder(new BCryptPasswordEncoder());

//        auth
//            .ldapAuthentication() // 基于 LDAP 的用户认证
//                .userSearchBase("ou=people")
//                .userSearchFilter("uid={0}")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .passwordCompare()
//                    .passwordAttribute("password")
//                    .passwordEncoder(new Md5PasswordEncoder()).and()
////                .contextSource().url("ldap://xxx.com:2333/dc=xxx,dc=com") // 配置 LDAP 服务器
//                .contextSource() // 配置嵌入式的 LDAP 服务器
//                    .root("dc=xxx,dc=com")
//                    .ldif("classpath:users.ldif");
    }

    // 配置通过拦截器保护请求
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    // 配置 Spring Security 的 Filter 链
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin() // 启用默认的登录页，默认URL='/login'
//                .loginPage("/login") // 设置自定义的登录页面
            .and()
                .logout()
                .logoutSuccessUrl("/")
            .and()
            .rememberMe() // 启用自动认证：cookie中存储密钥token
                .tokenRepository(new InMemoryTokenRepositoryImpl())
                .tokenValiditySeconds(30) // 配置时效30秒
                .key("spittrKey")
            .and()
            .httpBasic()
                .realmName("Spittr")
            .and()
            // 需要注意规则设置的顺序：
            // 最具体的请求路径放在前面，最不具体的路径放在最后面
            .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/spitter/me").authenticated()
                .antMatchers(HttpMethod.POST, "/spittles").authenticated()
                .anyRequest().permitAll();

        // 需要HTTPS
//        http.requiresChannel()
//            .antMatchers("/spitter/register")
//            .requiresSecure();
    }
}
