package orders.dao;

import orders.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author justZero
 * @since 2019/1/18
 */
public interface OrderDao extends MongoRepository<Order, String> {

    List<Order> findByCustomer(String customer);

    List<Order> findByCustomerLike(String customer);

    List<Order> findByCustomerAndType(String customer, String type);

    @Query("{customer:'Leo'}")
    List<Order> findLeoOrders();

    // 如果要传递参数，可使用 ?0, ?1 etc.和参数列表匹配
    @Query("{customer:'Leo', type:?0}")
    List<Order> findLeoOrdersByType(String type);
}
