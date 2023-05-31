package com.cloudera.message.client;

import java.util.Optional;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageReceiver {

    private static Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    private final BlockingDeque<String> dequeue;


    public MessageReceiver() {dequeue = new LinkedBlockingDeque<>();}

    public void receiveMessage(String message) {
        dequeue.addLast(message);
    }

    public Optional<String> takeMessage(long timeout, TimeUnit timeUnit) {
        try {
            return Optional.ofNullable(dequeue.pollLast(timeout, timeUnit));
        } catch (InterruptedException e) {
            LOGGER.error("Thread interrupted", e);
        }
        return Optional.empty();
    }
}
