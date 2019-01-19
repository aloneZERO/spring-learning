package cart.utills;

import cart.domain.Product;

import java.util.Random;
import java.util.UUID;

/**
 * @author justZero
 * @since 2019/1/19
 */
public class ProductUtil {

    private ProductUtil() {}

    private static Random randomUtil = new Random();

    public static Product random() {
        Product product = new Product();
        product.setSku(UUID.randomUUID().toString());
        product.setName("测试产品"+new Double(Math.random()*1000).intValue());
        product.setPrice(randomFloat(10, 100));
        return product;
    }

    private static float randomFloat(int min, int max) {
        Float val = ( min+((max-min)*randomUtil.nextFloat()) )*100;
        return val.intValue()*0.01f;
    }

}
