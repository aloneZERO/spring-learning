package spittr.test;

import org.junit.Test;
import spittr.exception.SpittleNotFoundException;

/**
 * @author justZero
 * @since 2019/1/5
 */
public class SpittrExceptionTest {

    @Test
    public void testSpittleNotFoundException() {
        SpittleNotFoundException exception = new SpittleNotFoundException("spittle not found");
        exception.printStackTrace();
    }

}
