package orders.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * Item 并非像关系型数据库一样，被独立出去，
 * 它仅是 Order 文档里内嵌的一部分而已。
 *
 * @author justZero
 * @since 2019-1-18
 */
@Data
public class Item {

    private String id;

    private Order order;

    private String product;

    private double price;

    private int quantity;

    public Item() {
        this.id = UUID.randomUUID().toString();
    }
}
