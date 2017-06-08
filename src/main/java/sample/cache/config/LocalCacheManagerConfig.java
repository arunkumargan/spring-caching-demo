package sample.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("default")
public class LocalCacheManagerConfig {

    @Bean
    public CacheManager inMemoryCacheManager() {
        SimpleCacheManager cacheManager =  new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("messages")));
        return cacheManager;
    }
}
