package sample.cache.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("pcc")
@EnableConfigurationProperties(CacheProperties.class)
public class GemfireCacheManagerConfig {
    
}
