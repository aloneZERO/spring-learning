package conditional.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 条件化创建 Bean
 * 条件 true 则创建该 Bean；false 则不。
 *
 * 若注解在类上，则该配置下的所有 bean 均受此条件影响。
 *
 * @author justZero
 * @since 2018/12/29
 */
@Configuration
public class MagicConfig {

    @Bean
    @Conditional(MagicExistsCondition.class)
    public MagicBean magicBean() {
        return new MagicBean();
    }

}
