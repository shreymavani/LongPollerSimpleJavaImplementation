package com.cloudera.message.listener;

import com.cloudera.message.client.MessageReceiver;

import java.util.ArrayList;
import java.util.List;

public class MessageListener {

    private final List<MessageReceiver> messageReceiverList=new ArrayList<MessageReceiver>();
    public void subscribe(MessageReceiver receiver) {
        messageReceiverList.add(receiver);
    }

    public void unSubscribe(MessageReceiver receiver) {
        messageReceiverList.remove(receiver);
    }
}
