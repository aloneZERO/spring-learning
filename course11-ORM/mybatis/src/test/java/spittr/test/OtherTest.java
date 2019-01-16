package test;

import org.junit.Test;

/**
 * @author justZero
 * @since 2019/1/15
 */
public class OtherTest {

    @Test
    public void test() {
        String str = "I am a string.";
        assert str.contains("a");
    }

    @Test(expected = AssertionError.class)
    public void testError() {
        assert false;
    }

}
