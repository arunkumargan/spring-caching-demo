package sample.cache.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cache.Message;
import sample.cache.ResponseMessage;
import sample.cache.service.MessageHandler;

@RestController
public class CacheDemonstratingController {
    
    private final MessageHandler messageHandler;
    
    public CacheDemonstratingController(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
    
    @PostMapping("/handle")
    public ResponseMessage handleMessage(@RequestBody Message message) {
        return this.messageHandler.handle(message);
    }
    
}
