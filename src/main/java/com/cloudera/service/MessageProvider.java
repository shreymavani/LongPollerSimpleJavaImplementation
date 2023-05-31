package com.cloudera.service;


import com.cloudera.message.cache.MessageCache;
import com.cloudera.message.cache.reader.CacheReader;
import com.cloudera.message.client.MessageReceiver;
import com.cloudera.message.listener.LatestMessageListener;
import com.cloudera.message.listener.MessageListener;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static com.cloudera.message.cache.reader.CacheReader.newCacheReader;
import static com.cloudera.message.listener.LatestMessageListener.newLatestMessageListener;

public class MessageProvider {

    private final MessageListener messageListener;

    private final MessageCache cache;
    private final RequestListener requestListener;

    private final ExecutorService cacheReaderThreads;

    public MessageProvider(MessageListener messageListener, MessageCache cache, RequestListener requestListener,ExecutorService cacheReaderThreads) {
        this.messageListener = messageListener;
        this.requestListener = requestListener;
        this.cache = cache;
        this.cacheReaderThreads = cacheReaderThreads;
    }

    public Optional<String>  waitForNextMessage(String tenantId, long timeout, TimeUnit timeUnit) {

//        if(requestListener.findDetails(tenantId)!= null) {

            final MessageReceiver receiver = new MessageReceiver();
            final CacheReader reader = newCacheReader(cache, receiver, tenantId, cacheReaderThreads).triggerCacheSearch();
            final LatestMessageListener latestMessageListener = newLatestMessageListener(receiver, messageListener).subscribe();
            try {
                return receiver.takeMessage(timeout, timeUnit);
            } finally {
                latestMessageListener.unSubscribe();
            }
//        }
//        return Optional.empty();
    }
}
