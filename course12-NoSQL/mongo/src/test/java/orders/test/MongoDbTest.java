package orders.test;

import orders.config.MongoConfig;
import orders.dao.OrderDao;
import orders.domain.Order;
import orders.utils.OrderUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author justZero
 * @since 2019/1/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
public class MongoDbTest {

    @Autowired
    private OrderDao orderDao;

    @Before
    public void cleanup() {
        // 每次测试方法执行前，删除所有订单，避免之前有错误测试的残留数据影响新的测试
        orderDao.deleteAll();
    }

    @Test
    public void testCreateOrders() {
        Order order = OrderUtil.createRandomOrder("Leo");
        OrderUtil.printOrder(order);
        order = orderDao.save(order);
        OrderUtil.printOrder(order);
    }

    @Test
    public void testAll() {
        assertEquals(0, orderDao.count());
        Order order = OrderUtil.createRandomOrder("Leo");
        OrderUtil.printOrder(order);

        // 保存一份订单
        Order savedOrder = orderDao.save(order);
        OrderUtil.printOrder(order);
        assertEquals(1, orderDao.count());

        // 通过 id 查找订单
        Order foundOrder = orderDao.findById(savedOrder.getId()).orElse(null);
        assertNotNull(foundOrder);
        assertEquals("Leo", foundOrder.getCustomer());
        assertEquals(2, foundOrder.getItems().size());

        // 通过某个字段值找到订单
        List<Order> leoOrders = orderDao.findByCustomer("Leo");
        assertEquals(1, leoOrders.size());
        assertEquals("Leo", leoOrders.get(0).getCustomer());
        assertEquals(2, leoOrders.get(0).getItems().size());

        // 通过某个字段值模糊查找订单
        List<Order> leoLikeOrders = orderDao.findByCustomerLike("Le");
        assertEquals(1, leoLikeOrders.size());
        assertEquals("Leo", leoLikeOrders.get(0).getCustomer());
        assertEquals(2, leoLikeOrders.get(0).getItems().size());

        // 通过多个字段值查找订单
        List<Order> leoTypeOrders = orderDao.findByCustomerAndType("Leo", "网站");
        assertEquals(1, leoTypeOrders.size());
        assertEquals("网站", leoTypeOrders.get(0).getType());
        assertEquals(2, leoTypeOrders.get(0).getItems().size());

        List<Order> testTypeOrders = orderDao.findByCustomerAndType("Leo", "手机");
        assertEquals(0, testTypeOrders.size());

        // 自定义的查询方法
        List<Order> leoOrders2 = orderDao.findLeoOrders();
        assertEquals(1, leoOrders2.size());
        assertEquals("Leo", leoOrders2.get(0).getCustomer());
        assertEquals(2, leoOrders2.get(0).getItems().size());

        // 删除一个订单
        orderDao.deleteById(savedOrder.getId());
        assertEquals(0, orderDao.count());
    }
}
