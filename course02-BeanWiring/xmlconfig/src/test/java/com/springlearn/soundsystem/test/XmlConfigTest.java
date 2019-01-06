package com.springlearn.soundsystem.test;

import com.springlearn.soundsystem.album.CompactDisc;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author justZero
 * @since 2018/12/29
 */
public class XmlConfigTest {

    private static ApplicationContext context;

    @BeforeClass
    public static void initContext() {
        context = new ClassPathXmlApplicationContext(
                "soundsystem.xml");
    }

    @Test
    public void testNewCD() {
        CompactDisc cd = context.getBean("compactDisc", CompactDisc.class);
        cd.play();

        cd = context.getBean("blankDisc", CompactDisc.class);
        cd.play();

        cd = context.getBean("blankDisc2", CompactDisc.class);
        cd.play();

        cd = context.getBean("blankDisc3", CompactDisc.class);
        cd.play();

        cd = context.getBean("blankDisc4", CompactDisc.class);
        cd.play();
    }

}
