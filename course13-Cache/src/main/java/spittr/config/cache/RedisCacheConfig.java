package spittr.config.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import spittr.po.Spitter;
import spittr.po.Spittle;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

/**
 * 使用 Redis 作为缓存
 *
 * @author justZero
 * @since 2019/1/20
 */
@Configuration
@EnableCaching
@PropertySource("classpath:redis.properties")
public class RedisCacheConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private Integer database;

    @Bean
    public RedisConnectionFactory redisCF() {
        RedisStandaloneConfiguration config =
                new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(RedisPassword.of(password));
        config.setDatabase(database);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory rcf) {
        RedisCacheConfiguration config = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(100))
                .disableCachingNullValues();

        return RedisCacheManager.builder(rcf)
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }

}
