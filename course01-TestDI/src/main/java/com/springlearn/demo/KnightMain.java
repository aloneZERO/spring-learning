package com.springlearn.demo;

import com.springlearn.demo.knight.Knight;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 初体验——依赖注入
 */
public class KnightMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("knight.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
    }

}
