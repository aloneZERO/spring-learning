package testdi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import testdi.config.KnightConfig;
import testdi.knight.Knight;

/**
 * @author justZero
 * @since 2019/2/4
 */
public class KnightMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(KnightConfig.class);
        Knight knight = context.getBean("knight", Knight.class);
        // 根据 KnightConfig 的配置，应该被注入了斩杀恶龙的骑士
        knight.embarkOnQuest();
    }

}
