package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 配置 Content Negotiating View Resovler
 * 根据请求的媒体类型，为其找到最佳视图。
 *
 * 这种方式并不推荐。推荐使用 HTTP 消息转换器的方式
 *
 * @author justZero
 * @since 2019-1-23
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
    "spittr.controller", "spittr.api"
})
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
        ContentNegotiatingViewResolver cnvr =
                new ContentNegotiatingViewResolver();
        cnvr.setContentNegotiationManager(cnm);
        return cnvr;
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.TEXT_HTML); // 默认 HTML
    }

    @Bean
    public ViewResolver beanNameViewResolver() {
        return new BeanNameViewResolver(); // 以 Bean 的形式查找视图
    }

    // 将 spittlesx 定义为 JSON 视图：
    // 如果逻辑视图的名称为 spittlesx，那么 BeanNameViewResolver 将会根据 bean
    // 名称匹配逻辑视图名称，即解析 spittlesx() 方法中声明的 View。
    // 没有匹配的 View 时，ContentNegotitingViewResolver 会采用默认的 HTML 类型解析。
    @Bean
    public View spittlesx() {
        return new MappingJackson2JsonView();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/register").setViewName("registerForm");
        registry.addViewController("/testapi").setViewName("testApi");
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
