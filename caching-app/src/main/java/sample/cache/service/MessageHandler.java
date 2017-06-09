package sample.cache.service;

import org.springframework.cache.annotation.Cacheable;
import sample.cache.model.Message;
import sample.cache.model.ResponseMessage;

public interface MessageHandler {

    @Cacheable(cacheNames = "messages", key = "#p0.id")
    ResponseMessage handle(Message request);

}

