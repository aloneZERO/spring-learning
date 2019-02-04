package externals.demo.test;

import externals.demo.config.ExpressiveConfig;
import externals.demo.disc.Disc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author justZero
 * @since 2018/12/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExpressiveConfig.class)
public class ExternalsTest {

    @Autowired
    @Qualifier("disc0")
    private Disc disc0;

    @Autowired
    @Qualifier("disc1")
    private Disc disc1;

    @Autowired
    @Qualifier("disc2")
    private Disc disc2;

    @Test
    public void testLoadValByEnv() {
        disc0.info();
        assertEquals("Sad!", disc0.getTitle());
        assertEquals("Xxxtentacion", disc0.getArtist());
    }

    @Test
    public void testLoadValByPlaceholder() {
        disc1.info();
        assertEquals("Look at me", disc1.getTitle());
        assertEquals("Xxxtentacion", disc1.getArtist());
    }

    @Test
    public void testDefaultVal() {
        disc2.info();
        assertEquals("unknown", disc2.getTitle());
        assertEquals("unknown", disc2.getArtist());
    }

}
