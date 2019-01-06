package com.springlearn.demo.test;

import com.springlearn.demo.knight.Knight;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 初体验——面向切面编程
 *
 * @author justZero
 * @since 2018/12/28
 */
public class KnightTest {

    private static ApplicationContext context;

    /**
     * Before 每个测试方法运行前都会执行一次；
     * BeforeClass 测试类整个生命周期只执行一次。
     */
    @BeforeClass
    public static void initContext() {
        context = new ClassPathXmlApplicationContext("knight.xml");
    }

    @Test
    public void testKnightWithMinstrel() {
        Knight knight = context.getBean("knight", Knight.class);
        knight.embarkOnQuest();
    }

}
