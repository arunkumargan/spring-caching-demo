package sample.cache.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sample.cache.model.Message;
import sample.cache.model.ResponseMessage;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.data.redis.repositories.enabled=false")
@DirtiesContext
public class MessageHandlerNoCacheTest {
    
    @Autowired
    private MessageHandler messageHandler;
    
    @Test
    public void testUnCachedCalls() {
        assertThat(this.messageHandler.handle(new Message("1", "payload", false, 0L)))
                .isEqualTo(new ResponseMessage("id", "something", "1"));
        assertThat(this.messageHandler.handle(new Message("1", "payload", false, 0L)))
                .isEqualTo(new ResponseMessage("id", "something", "2"));
        assertThat(this.messageHandler.handle(new Message("1", "payload", false, 0L)))
                .isEqualTo(new ResponseMessage("id", "something", "3"));
        assertThat(this.messageHandler.handle(new Message("1", "payload", false, 0L)))
                .isEqualTo(new ResponseMessage("id", "something", "4"));
    }
    
    @SpringBootApplication(exclude = RedisAutoConfiguration.class)
    static class Config {
        
        @Bean
        public MessageHandler messageHandler() {
            AtomicInteger state = new AtomicInteger();
            MessageHandler mock = mock(MessageHandler.class);
            when(mock.handle(any(Message.class))).then(invocation -> {
                int i = state.incrementAndGet();
                return new ResponseMessage("id", "something", "" + i);
            });
            return mock;
        }
        
        @Bean
        public CacheManager inMemoryCacheManager() {
            SimpleCacheManager cacheManager =  new SimpleCacheManager();
            cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("messages")));
            return cacheManager;
        }
        
    }
}
