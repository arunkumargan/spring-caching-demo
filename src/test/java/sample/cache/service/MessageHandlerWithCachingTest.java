package sample.cache.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sample.cache.Message;
import sample.cache.ResponseMessage;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.data.redis.repositories.enabled=false")
@DirtiesContext
public class MessageHandlerWithCachingTest {
    
    @Autowired
    private MessageHandler messageHandler;
    
    @Test
    public void testCachedCall() {
        assertThat(this.messageHandler.handle(new Message("1", "payload", false, 0L)))
                .isEqualTo(new ResponseMessage("id", "something", "1"));
        assertThat(this.messageHandler.handle(new Message("1", "payload", false, 0L)))
                .isEqualTo(new ResponseMessage("id", "something", "1"));
        assertThat(this.messageHandler.handle(new Message("1", "payload", false, 0L)))
                .isEqualTo(new ResponseMessage("id", "something", "1"));
        assertThat(this.messageHandler.handle(new Message("1", "payload", false, 0L)))
                .isEqualTo(new ResponseMessage("id", "something", "1"));
    }
    
    @TestConfiguration
    @EnableCaching
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
    }
}
