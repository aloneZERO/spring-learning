package spittr.config.cache;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 使用 EhCache 缓存
 *
 * @author justZero
 * @since 2019/1/20
 */
@Configuration
@EnableCaching
public class EhCacheConfig {

    // 用于创建 EhCache CacheManager 实例
    @Bean
    public EhCacheManagerFactoryBean ehCache() {
        EhCacheManagerFactoryBean factoryBean =
                new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(
                new ClassPathResource("cache/ehcache.xml"));
        return factoryBean;
    }

    // 注入 EhCache CacheManager 实例，
    // 创建 Spring EhCacheCacheManager Bean
    @Bean
    public EhCacheCacheManager cacheCacheManager(CacheManager cm) {
        return new EhCacheCacheManager(cm);
    }

}
