package spittr.config.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring 内置的缓存实现（不推荐使用）
 *
 * @author justZero
 * @since 2019/1/20
 */
@Configuration
@EnableCaching
public class SpringCacheConfig {

    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        // 底层是 java.util.concurrent.ConcurrentHashMap
        return new ConcurrentMapCacheManager();
    }

}
