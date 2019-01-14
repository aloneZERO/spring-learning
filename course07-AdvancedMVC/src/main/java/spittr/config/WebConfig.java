package spittr.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author justZero
 * @since 2019/1/1
 */
@Configuration
@EnableWebMvc
@ComponentScan("spittr.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    // 配置静态资源访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/css/**").addResourceLocations("/static/css/");
        registry.addResourceHandler("/static/js/**").addResourceLocations("/static/js/");
        registry.addResourceHandler("/static/img/**").addResourceLocations("/static/img/");
    }

    // 配置视图解析
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/static/");
        resolver.setSuffix(".html");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }
    /**
     * 隐藏资源后缀：
     * RESTFUL风格
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/spittles").setViewName("spittles");
        registry.addViewController("/spittle").setViewName("spittle");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/profile").setViewName("profile");
    }

    // 配置JSON解析
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                // Long值过大，JS会发生精度缺失，故配置为String解析
                .serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")) // 处理日期
                .serializationInclusion(JsonInclude.Include.NON_NULL);  // 不显示NULL值字段

        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 配置文件上传
//    @Bean
//    public MultipartResolver multipartResolver() throws IOException {
//        // 仅适用于支持Servlet3.0+的容器
//        return new StandardServletMultipartResolver();
//    }

    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        // 替代方案：Spring提供的实现，可部署到非Servlet3.0的容器中
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(
                new FileSystemResource("c:/users/Administrator/Desktop/Recent/upload"));
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(5*1024*1024);
        multipartResolver.setMaxInMemorySize(0);
        return multipartResolver;
    }
}
