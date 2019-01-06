package com.springlearn.demo.config;

import com.springlearn.demo.disc.BlankDisc;
import com.springlearn.demo.disc.Disc;
import com.springlearn.demo.disc.OtherDisc;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * 注入外部值
 * 例如：加载属性文件
 *
 * @author justZero
 * @since 2018/12/30
 */
@Configuration
@AllArgsConstructor
@PropertySource("classpath:app.properties")
public class ExpressiveConfig {

    Environment env;

    @Bean
    @Qualifier("disc0")
    public Disc disc() {
        return new BlankDisc(
                env.getProperty("disc0.title"),
                env.getProperty("disc0.artist"));
    }

    @Bean
    @Qualifier("disc1")
    public Disc disc1() {
        return new OtherDisc();
    }

    @Bean
    @Qualifier("disc2")
    public Disc disc2() {
        return new BlankDisc(
                // 找不到相应属性，则设置默认值
                env.getProperty("disc2.title", "unknown"),
                env.getProperty("disc2.artist", "unknown"));
    }

    /*
     * 使用占位符加载属性值，
     * 必须配置PropertySourcesPlaceholderConfigurer bean。
     * 但是此处使用的是Spring4.3.0并不需要配置该bean
     */
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

}
