package com.cloudera.apiHandler;

import com.cloudera.message.cache.MessageCache;
import com.cloudera.message.listener.MessageListener;
import com.cloudera.service.RequestListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class LongPollingConfig {
    @Bean
    public MessageCache messageCache() {
        // Create and configure the MessageCache bean
        return new MessageCache();
    }

    @Bean
    public ExecutorService executorService() {
        // Create and configure the ExecutorService bean
        return Executors.newFixedThreadPool(10);
    }

    @Bean
    public MessageListener messageListener() {
        // Create and configure the MessageListener bean
        return new MessageListener();
    }

    @Bean
    RequestListener requestListener(){
        return new RequestListener();
    }
}
