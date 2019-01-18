package orders.domain;

import java.util.Collection;
import java.util.LinkedHashSet;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class Order {

    @Id
    private String id;

    // 设置文档中的字段名，默认与属性名对应
    @Field("customer")
    private String customer;

    private String type;

    private Collection<Item> items = new LinkedHashSet<>();

}
