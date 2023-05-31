package com.cloudera.message.listener;

import com.cloudera.message.client.MessageReceiver;

public class LatestMessageListener {

    private final MessageReceiver receiver;
    private final MessageListener messageListener;


    private LatestMessageListener(MessageReceiver receiver, MessageListener messageListener) {
        this.receiver = receiver;
        this.messageListener = messageListener;
    }

    public static LatestMessageListener newLatestMessageListener(MessageReceiver receiver, MessageListener messageListener) {
        return new LatestMessageListener(receiver, messageListener);
    }

    public LatestMessageListener subscribe() {
        messageListener.subscribe(receiver);
        return this;
    }

    public LatestMessageListener unSubscribe() {
        messageListener.unSubscribe(receiver);
        return this;
    }
}
