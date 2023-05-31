package com.cloudera.message.cache.writer;

import com.cloudera.message.cache.MessageCache;
import com.cloudera.message.client.MessageReceiver;

import java.util.concurrent.ExecutorService;

public class CacheWriter {
    private final MessageCache cache;
    private final String tenantId;
    private final String newMessage;

    private final ExecutorService cacheWriterThreads;
    public CacheWriter(MessageCache cache, String tenantId,
                        String newMessage, ExecutorService cacheWriterThreads) {
        this.cache = cache;
        this.tenantId = tenantId;
        this.newMessage = newMessage;
        this.cacheWriterThreads = cacheWriterThreads;
    }
    public void addMessageToCache(){
        cache.addMessage(tenantId,newMessage);
    }
}