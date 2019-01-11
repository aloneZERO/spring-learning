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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/css/**").addResourceLocations("/static/css/");
        registry.addResourceHandler("/static/js/**").addResourceLocations("/static/js/");
        registry.addResourceHandler("/static/img/**").addResourceLocations("/static/img/");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/static/");
        resolver.setSuffix(".html");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/spittles").setViewName("spittles");
        registry.addViewController("/spittle").setViewName("spittle");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/profile").setViewName("profile");
        //        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                // Long值过大，JS会发生精度缺失，故解析成String
                .serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .serializationInclusion(JsonInclude.Include.NON_NULL);

        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(
                new FileSystemResource("c:/users/Administrator/Desktop/Recent/upload"));
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(5*1024*1024);
        multipartResolver.setMaxInMemorySize(0);
        return multipartResolver;
    }
}
