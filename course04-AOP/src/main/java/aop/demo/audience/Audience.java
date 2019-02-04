package aop.demo.audience;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author justZero
 * @since 2018/12/31
 */
@Aspect
@Component
public class Audience {

    /**
     * execution(* *.Performance.perform(..))
     * 方法执行时触发(返回任意类型 任何类.perform(任意参数))
     * 此处限定 NewYearConcert 类的 perform() 方法才会触发
     *
     * Poincut 注解定义命名的切点，一次定义，多处重用。
     * performance 方法内容为空即可，它只是 @Poincut 的依附。
     */
    @Pointcut("execution(* aop.demo.concert.NewYearConcert.perform(..))")
    private void performance() {}

    @Before("performance()")
    public void silenceCellPhone() {
        System.out.println("手机调成静音...");
    }

    @Before("performance()")
    public void takeSeat() {
        System.out.println("坐在座位上...");
    }

    @AfterReturning("performance()")
    public void applause() {
        System.out.println("鼓掌！啪啪啪！");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("演砸了？退钱！！！");
    }

}
