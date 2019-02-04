package aop.demo.audience;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author justZero
 * @since 2018/12/31
 */
@Aspect
@Component
public class HighFan {

    @Pointcut("execution(* aop.demo.concert.HigherBrothersLiveHouse.perform(..))")
    private void performance() {}

    /**
     * Around（环绕通知）注解可同时实现多个通知
     */
    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("买票...");
            joinPoint.proceed(); // 调用被通知方法，此处即perform()
            System.out.println("嗨嗨嗨！！！");
        } catch (Throwable e) {
            System.out.println("今天不嗨啊，想退票");
        }
    }

}
