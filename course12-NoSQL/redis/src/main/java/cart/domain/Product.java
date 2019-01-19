package cart.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

/**
 * @author justZero
 * @since 2019/1/19
 */
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = -826681094120465645L;

    private String sku; // 产品统一编号
    private String name;
    private float price;
}
