package com.cloudera.message.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageCache {

    private final Map<String,String> tenatIdToMessageMap;


    public MessageCache() {tenatIdToMessageMap = new ConcurrentHashMap<>();}

    public void addMessage(String key, String value) {
        tenatIdToMessageMap.put(key, value);
    }

    public String findMessage(String key) {
        return tenatIdToMessageMap.get(key);
    }

    public void getAllMessage(){System.out.println(tenatIdToMessageMap);}
}
