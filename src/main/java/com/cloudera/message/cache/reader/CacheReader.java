package com.cloudera.message.cache.reader;

import com.cloudera.message.cache.MessageCache;
import com.cloudera.message.client.MessageReceiver;

import java.util.concurrent.ExecutorService;

public class CacheReader {

    private final MessageCache cache;

    private final MessageReceiver receiver;

    private final String tenantId;

    private final ExecutorService cacheReaderThreads;

    public CacheReader(MessageCache cache, MessageReceiver receiver, String tenantId,
                       ExecutorService cacheReaderThreads) {
        this.cache = cache;
        this.receiver = receiver;
        this.tenantId = tenantId;
        this.cacheReaderThreads = cacheReaderThreads;
    }

    public static CacheReader newCacheReader(MessageCache cache, MessageReceiver receiver, String tenantId,ExecutorService cacheReaderThreads) {
        return new CacheReader(cache, receiver, tenantId,cacheReaderThreads);
    }
    public CacheReader triggerCacheSearch() {
        cacheReaderThreads.submit(()-> receiver.receiveMessage(cache.findMessage(tenantId)));
        return this;
    }


}
