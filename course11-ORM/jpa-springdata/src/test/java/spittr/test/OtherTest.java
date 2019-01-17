package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testLoop() {
        List<String> list = new ArrayList<>();
        list.add("no.1");
        list.add(null);
        list.add("no.2");
        for (String s: list) {
            System.out.println(s);
        }
    }

}
