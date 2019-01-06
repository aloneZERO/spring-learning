package com.springlearn.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * 条件化创建Bean
 * 条件true则创建该Bean；false则不
 *
 * @author justZero
 * @since 2018/12/29
 */
@Conditional(MagicExistsCondition.class)
public class MagicConfig {

    @Bean
    public MagicBean magicBean() {
        return new MagicBean();
    }

}
