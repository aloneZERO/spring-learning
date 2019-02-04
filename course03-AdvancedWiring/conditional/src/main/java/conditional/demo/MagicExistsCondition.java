package conditional.demo;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author justZero
 * @since 2018/12/29
 */
public class MagicExistsCondition implements Condition {


    // 如果有 key 为 magic 的环境变量或 JVM 属性，则条件为真
    @Override
    public boolean matches(ConditionContext context,
                           AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env.containsProperty("magic");
    }
}
