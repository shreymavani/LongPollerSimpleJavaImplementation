package com.cloudera.service;

import java.util.concurrent.TimeUnit;

public class RequestHandler {

    private final MessageProvider messageProvider;

    public RequestHandler(MessageProvider messageProvider) {this.messageProvider = messageProvider;}

    public String request(String tenantId) {
        return messageProvider.waitForNextMessage(tenantId, 5, TimeUnit.SECONDS).orElse("");
    }
}
