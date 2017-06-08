package sample.cache.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sample.cache.Message;
import sample.cache.ResponseMessage;

import java.time.Duration;

@Service
public class MessageHandlerImpl implements MessageHandler {
    @Override
    public ResponseMessage handle(Message request) {
        return Mono
                .delay(Duration.ofMillis(request.getDelayBy()))
                .map(t -> request)
                .map(m -> {
                    if (m.getThrowException()) {
                        throw new RuntimeException("A deliberate Exception!");
                    }
                    
                    return new ResponseMessage(request.getId(), request.getPayload(), "Ack: Thanks");
                }).block();
    }
}
