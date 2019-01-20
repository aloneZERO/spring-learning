package spittr.config.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;
import java.util.Collections;

/**
 * 同时使用多个缓存管理器
 * 使用时，迭代这些缓存管理器，以查找之前缓存的值
 *
 * @author justZero
 * @since 2019/1/20
 */
@Configuration
@EnableCaching
@PropertySource("classpath:redis.properties")
public class CompositeCacheConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private Integer database;

    @Bean
    public CompositeCacheManager cacheManager(
            net.sf.ehcache.CacheManager ehCM,
            RedisConnectionFactory redisCF) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(100))
                .disableCachingNullValues();
        RedisCacheManager redisCM = RedisCacheManager.builder(redisCF)
                .cacheDefaults(config)
                .withInitialCacheConfigurations(
                        Collections.singletonMap("SpittrCache", config))
                .transactionAware()
                .build();

        return new CompositeCacheManager(
                new EhCacheCacheManager(ehCM), redisCM);
    }

    @Bean
    public EhCacheManagerFactoryBean ehCache() {
        EhCacheManagerFactoryBean factoryBean =
                new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(
                new ClassPathResource("cache/ehcache.xml"));
        return factoryBean;
    }

    @Bean
    public RedisConnectionFactory redisCF() {
        RedisStandaloneConfiguration config =
                new RedisStandaloneConfiguration(host, port);
        config.setPassword(RedisPassword.of(password));
        config.setDatabase(database);
        return new JedisConnectionFactory(config);
    }

}
