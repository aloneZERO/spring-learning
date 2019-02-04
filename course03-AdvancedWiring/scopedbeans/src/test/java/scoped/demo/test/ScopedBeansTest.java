package scoped.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import scoped.demo.Notepad;
import scoped.demo.UniqueThing;
import scoped.demo.config.ScopedConfig;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

/**
 * @author justZero
 * @since 2019/2/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ScopedConfig.class)
public class ScopedBeansTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testProxyScope() {
        Notepad notepad1 = context.getBean(Notepad.class);
        Notepad notepad2 = context.getBean(Notepad.class);
        assertNotSame(notepad1, notepad2);
    }

    @Test
    public void testSingletonScope() {
        UniqueThing thing1 = context.getBean(UniqueThing.class);
        UniqueThing thing2 = context.getBean(UniqueThing.class);
        assertSame(thing1, thing2);
    }

}
