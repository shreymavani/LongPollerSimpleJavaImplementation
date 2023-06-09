package com.cloudera.apiHandler;

import com.cloudera.message.cache.MessageCache;
import com.cloudera.message.cache.writer.CacheWriter;
import com.cloudera.message.listener.MessageListener;
import com.cloudera.service.MessageProvider;
import com.cloudera.service.RequestHandler;
import com.cloudera.service.RequestListener;
import model.ClientDataResponse;
import model.ClusterDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Log4j
@RestController
@Component
@RequestMapping("/demo")
public class LongPollingController {

    private final MessageCache messageCache;
    private CacheWriter cacheWriter;
    private  RequestHandler requestHandler;
    private RequestListener requestListener;
    private MessageProvider messageProvider;
    private final ExecutorService executorService;
    private final MessageListener messageListener;

    @Autowired
    LongPollingController(MessageCache messageCache, ExecutorService executorService,MessageListener messageListener,RequestListener requestListener)
    {
        this.messageCache = messageCache;
        this.executorService = executorService;
        this.messageListener = messageListener;
        this.requestListener = requestListener;
        System.out.println("Initialization Done!!");
    }

    @PostMapping(value = "/sendMessage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveMessage(@RequestBody ClusterDetails message) {        //Consider ClusterId and TenantId is same as of now

        System.out.println("Inside Post Request");
        new CacheWriter(messageCache, message.getId(),message.getMessage(),executorService).addMessageToCache();     //TenantID Missing in cache
        messageListener.sentToALLReceivers(message.getMessage());
        messageCache.getAllMessage();
        return ResponseEntity.ok("Data Inserted Successfully");
    }

    @PostMapping(value = "/activeSession", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCache(@RequestBody ClusterDetails details){
        requestListener.addDetails(details.getId(),details.getMessage());
        return ResponseEntity.ok("Active Session Cache Updated");
    }
    @GetMapping("/getMessages")
    public ResponseEntity<ClientDataResponse> getMessage(String tenantId) throws InterruptedException {
        System.out.println("Inside Get Request");
        messageProvider = new MessageProvider(messageListener,messageCache,requestListener,executorService);
        requestHandler = new RequestHandler(messageProvider);
        String data =requestHandler.request(tenantId);
//        System.out.println("Get the Data");
//        messageCache.getAllMessage();
        return ResponseEntity.ok(new ClientDataResponse(tenantId,data));
    }

}