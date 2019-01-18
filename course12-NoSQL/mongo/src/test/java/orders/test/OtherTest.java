package orders.test;

import orders.utils.OrderUtil;
import org.junit.Test;

/**
 * @author justZero
 * @since 2019/1/18
 */
public class OtherTest {

    @Test
    public void test() {
        System.out.println(Math.floor(2.33));
        assert Math.floor(2.33)==2;
    }

    @Test
    public void testOrderUtil() {
        for (int i=0; i< 5; i++) {
            System.out.println(OrderUtil.randomType());
        }
        OrderUtil.printOrder(
                OrderUtil.createRandomOrder("leo"));
    }

}
