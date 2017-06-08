package sample.cache.service;

import org.springframework.cache.annotation.Cacheable;
import sample.cache.Message;
import sample.cache.ResponseMessage;

public interface MessageHandler {

    @Cacheable(cacheNames = "messages")
    ResponseMessage handle(Message request);

}

