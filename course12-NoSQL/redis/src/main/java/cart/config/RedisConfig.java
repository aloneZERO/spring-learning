package cart.config;

import cart.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author justZero
 * @since 2019/1/19
 */
@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private Integer database;

    @Value("${redis.maxIdle}")
    private Integer maxIdle;
    @Value("${redis.maxTotal}")
    private Integer maxTotal;
    @Value("${redis.maxWait}")
    private Integer maxWait;
    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    // redis 基本配置
    @Bean
    public RedisStandaloneConfiguration standaloneConfig() {
        RedisStandaloneConfiguration basicConfig = new RedisStandaloneConfiguration();
        basicConfig.setHostName(host);
        basicConfig.setPort(port);
        basicConfig.setPassword(RedisPassword.of(password));
        basicConfig.setDatabase(database);
        return basicConfig;
    }

    // redis 连接池配置
    @Bean
    public JedisClientConfiguration poolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(testOnBorrow);
        return JedisClientConfiguration.builder()
                .usePooling().poolConfig(poolConfig)
                .build();
    }

    @Bean
    public RedisConnectionFactory redisCF(
            RedisStandaloneConfiguration basicConfig,
            JedisClientConfiguration poolConfig) {
        return new JedisConnectionFactory(basicConfig, poolConfig);
    }

    @Bean
    public RedisTemplate<String, Product> redisTemplate(
            RedisConnectionFactory redisCF) {
        RedisTemplate<String, Product> redis = new RedisTemplate<>();
        redis.setConnectionFactory(redisCF);
        return redis;
    }
}
