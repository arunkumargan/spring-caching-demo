package sample.cache.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
@Profile("redis")
@EnableConfigurationProperties(CacheProperties.class)
public class RedisCacheManagerConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate, 
                                          CacheProperties cacheProperties) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);
        List<String> cacheNames = cacheProperties.getCacheNames();
        if (!cacheNames.isEmpty()) {
            cacheManager.setCacheNames(cacheNames);
        }
        return cacheManager;
    }
    
    @Configuration
    @Profile("cloud")
    static class CloudConfig extends AbstractCloudConfig {
        
        @Bean
        public RedisConnectionFactory redisFactory() {
            return connectionFactory().redisConnectionFactory();
        }
        
    }
    
}
