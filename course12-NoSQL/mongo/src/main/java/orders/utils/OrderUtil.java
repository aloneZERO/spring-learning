package orders.utils;

import orders.domain.Item;
import orders.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author justZero
 * @since 2019/1/18
 */
public class OrderUtil {

    private OrderUtil() {}

    public static final String WEB = "网站";
    public static final String PHONE = "手机";

    public static String randomType() {
        int flag = new Random().nextInt(2);
        return flag==0 ? WEB : PHONE;
    }

    public static Order createRandomOrder(String customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setType(randomType());
        Random random = new Random();
        int itemNum = random.nextInt(10);
        List<Item> items = new ArrayList<>(itemNum);
        for (int i=0; i< itemNum; i++) {
            Item item = new Item();
            item.setProduct("测试产品"+random.nextFloat());
            item.setQuantity(random.nextInt(21));
            item.setPrice( Math.floor((10+((100-10)*random.nextDouble()))*100/1)*0.01 );
            items.add(item);
        }
        order.setItems(items);
        return order;
    }

    public static void printOrder(Order order) {
        System.out.println("----------- "+order.getId()+" -----------");
        System.out.println("Customer: "+order.getCustomer());
        System.out.println("Type: "+order.getType());
        System.out.println("Items:");
        for (Item item: order.getItems()) {
            System.out.println("    id-"+item.getId()+
                    ", product-"+item.getProduct()+
                    ", price-"+item.getPrice()+
                    ", quantity-"+item.getQuantity());
        }
        System.out.println("--------------------------------------------\n");
    }

}
