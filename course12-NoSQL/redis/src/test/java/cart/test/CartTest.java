package cart.test;

import cart.config.RedisConfig;
import cart.domain.Product;
import cart.utills.ProductUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author justZero
 * @since 2019/1/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class CartTest {

    @Autowired
    private RedisConnectionFactory rcf;

    @Autowired
    private RedisTemplate<String, Product> redis;

    // 清空当前数据库所有 key，慎用哦~
//    @Test
//    public void cleanAll() {
//        rcf.getConnection().flushDb();
//    }

    private static final Set<String> DEL_KEY_SET = new HashSet<>();
    @After
    public void cleanup() {
        redis.delete(DEL_KEY_SET);
    }

    /**
     * 注意：默认 Redis Template 使用的序列化器是 JdkSerializationRedisSerializer.
     * 所以当你在 redis 命令行查看结果时，会发现乱码一样的数据。
     * 用 get "key" 这种方式获取值时，也会得到空结果，因为 key 不是字符串（而是二进制）。
     * 如果想像平常一样操作 redis 数据，如下设置新的序列化器即可：本类也有相关的测试方法供参考
     * redis.setKeySerializer(new StringRedisSerializer()); // 以字符串的方式序列化 key
     */
    @Test
    public void testSimpleValues() {
        Product product = ProductUtil.random();

        redis.opsForValue().set(product.getSku(), product);

        Product found = redis.opsForValue().get(product.getSku());
        assertEquals(product.getSku(), found.getSku());
        assertEquals(product.getName(), found.getName());
        assertEquals(product.getPrice(), found.getPrice(), 0.005);

        DEL_KEY_SET.add(product.getSku());
    }

    @Test
    public void testList() {
        Product product = ProductUtil.random();
        Product product2 = ProductUtil.random();
        Product product3 = ProductUtil.random();

        // list 尾部插入数据
        redis.opsForList().rightPush("cart", product);
        redis.opsForList().rightPush("cart", product2);
        redis.opsForList().rightPush("cart", product3);

        // 测试 list 'cart' 的长度
        assertEquals(3, redis.opsForList().size("cart").longValue());

        Product first = redis.opsForList().leftPop("cart"); // list 首部弹出数据
        Product last = redis.opsForList().rightPop("cart"); // list 尾部弹出数据

        assertEquals(product.getSku(), first.getSku());
        assertEquals(product.getName(), first.getName());
        assertEquals(product.getPrice(), first.getPrice(), 0.005);

        assertEquals(product3.getSku(), last.getSku());
        assertEquals(product3.getName(), last.getName());
        assertEquals(product3.getPrice(), last.getPrice(), 0.005);

        assertEquals(1, redis.opsForList().size("cart").longValue());

        DEL_KEY_SET.add("cart");
    }

    @Test
    public void testList_range() {
        for(int i=0; i < 30; i++) {
            Product product = new Product();
            product.setSku("SKU-" + i);
            product.setName("PRODUCT " + i);
            product.setPrice(i + 0.99f);
            redis.opsForList().rightPush("cart", product);
        }

        assertEquals(30, redis.opsForList().size("cart").longValue());

        // 取 list 'cart' 索引2-12的数据，索引从0开始
        List<Product> products = redis.opsForList().range("cart", 2, 12);
        for(int i=0; i < products.size(); i++) {
            Product product = products.get(i);
            assertEquals("SKU-" + (i+2), product.getSku());
            assertEquals("PRODUCT " + (i+2), product.getName());
            assertEquals(i + 2 + 0.99f, product.getPrice(), 0.005);
        }

        DEL_KEY_SET.add("cart");
    }

    @Test
    public void testSet() {
        Product product = ProductUtil.random();

        redis.opsForSet().add("cart", product);
        assertEquals(1, redis.opsForSet().size("cart").longValue());

        DEL_KEY_SET.add("cart");
    }

    @Test
    public void testSet_setOperations() {
        for(int i=0; i < 30; i++) {
            Product product = new Product();
            product.setSku("SKU-" + i);
            product.setName("PRODUCT " + i);
            product.setPrice(i + 0.99f);
            redis.opsForSet().add("cart1", product);
            // cart2 是 cart1 的子集
            if (i%3 == 0) {
                redis.opsForSet().add("cart2", product);
            }
        }

        Set<Product> diff = redis.opsForSet().difference("cart1", "cart2"); // 集合差集
        Set<Product> union = redis.opsForSet().union("cart1", "cart2");     // 集合并集
        Set<Product> isect = redis.opsForSet().intersect("cart1", "cart2"); // 集合交集

        assertEquals(20, diff.size());
        assertEquals(30, union.size());
        assertEquals(10, isect.size());
        System.out.println("\n========= 差集 =========");
        diff.forEach( p->System.out.println(p.getSku()) );
        System.out.println("========= 并集 =========");
        union.forEach( p->System.out.println(p.getSku()) );
        System.out.println("========= 交集 =========");
        isect.forEach( p->System.out.println(p.getSku()) );

        // 随机从 set 'cart1' 中取一个数据
        Product random = redis.opsForSet().randomMember("cart1");
        assertNotNull(random);

        DEL_KEY_SET.add("cart1");
        DEL_KEY_SET.add("cart2");
    }

    @Test
    public void bindingToAKey() {
        Product product = ProductUtil.random();
        Product product2 = ProductUtil.random();
        Product product3 = ProductUtil.random();

        // 绑定一个 key，每次操作都在绑定的 key 上执行
        // 当大量操作都对同一个 key 时，这种方式会让代码简洁
        BoundListOperations<String, Product> cart = redis.boundListOps("cart");
        cart.rightPush(product);
        cart.rightPush(product2);
        cart.rightPush(product3);

        assertEquals(3, cart.size().longValue());

        Product first = cart.leftPop();
        Product last = cart.rightPop();

        assertEquals(product.getSku(), first.getSku());
        assertEquals(product.getName(), first.getName());
        assertEquals(product.getPrice(), first.getPrice(), 0.005);

        assertEquals(product3.getSku(), last.getSku());
        assertEquals(product3.getName(), last.getName());
        assertEquals(product3.getPrice(), last.getPrice(), 0.005);

        assertEquals(1, cart.size().longValue());

        DEL_KEY_SET.add("cart");
    }

    @Test
    public void setKVSerializers() {
        RedisTemplate<String, Product> redis = new RedisTemplate<>();
        redis.setConnectionFactory(rcf);
        // 设置 key 以字符串格式序列化，value 以 JSON 格式序列化
        redis.setKeySerializer(new StringRedisSerializer());
        redis.setValueSerializer(new Jackson2JsonRedisSerializer<>(Product.class));
        redis.afterPropertiesSet();

        Product product = ProductUtil.random();

        redis.opsForValue().set(product.getSku(), product);

        Product found = redis.opsForValue().get(product.getSku());
        assertEquals(product.getSku(), found.getSku());
        assertEquals(product.getName(), found.getName());
        assertEquals(product.getPrice(), found.getPrice(), 0.005);

        // key 和 value 均以字符串格式解析
        StringRedisTemplate stringRedis = new StringRedisTemplate(rcf);
        String json = stringRedis.opsForValue().get(product.getSku());
        System.out.println("\n"+json);

        DEL_KEY_SET.add(product.getSku());
    }

    @Test
    public void saveSomeProducts() {
        redis.setKeySerializer(new StringRedisSerializer());
        redis.setValueSerializer(new Jackson2JsonRedisSerializer<>(Product.class));
        for (int i=0; i< 5; i++) {
            Product product = ProductUtil.random();
            System.out.println(product);
            redis.opsForValue().set(product.getSku(), product);
            redis.opsForList().rightPush("repo", product);
        }
    }

}
