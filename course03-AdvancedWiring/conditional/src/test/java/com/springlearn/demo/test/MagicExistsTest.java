package com.springlearn.demo.test;

import static org.junit.Assert.*;

import com.springlearn.demo.MagicConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MagicConfig.class)
public class MagicExistsTest {

    @Autowired
    private ApplicationContext context;

    @BeforeClass
    public static void setup() {
        System.setProperty("magic", "+1s");
    }

    @Test
    public void shouldBeFalse() {
        assertFalse(context.containsBean("magicBean"));
    }

    @Test
    public void shouldBeTrue() {
        assertTrue(context.containsBean("magicBean"));
    }

}
