package aop.demo;

import aop.demo.concert.Performance;
import aop.demo.config.ConcertConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 古代有好多英雄，他们有很多自己的丰功伟绩。如果他们想要把自己的
 * 故事记录下来，肯定不会自己动手，那是史官的活。这就是AOP存在的
 * 意义。
 *
 * 某些功能必不可少（日志、事务等），但又和业务逻辑无关，没有自己
 * 实现和主动调用的必要，这时，就可以利用AOP技术把这些功能模块化，
 * 然后织入业务功能里。
 *
 * @author justZero
 * @since 2018-12-31
 */
public class DemoMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ConcertConfig.class);

        Performance concert = context
                .getBean("newYear", Performance.class);
        concert.perform();

        System.out.println("\n下一场表演\n");

        concert = context
                .getBean("higherBrothers", Performance.class);
        concert.perform();
    }

}
