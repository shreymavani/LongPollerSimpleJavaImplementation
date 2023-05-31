package com.cloudera.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestListener {

    private Map<String,String> ActiveSession;

    public RequestListener() {ActiveSession = new ConcurrentHashMap<>();}

    public void addDetails(String key, String value) {
        ActiveSession.put(key, value);
    }

    public String findDetails(String key) {
        return ActiveSession.get(key);
    }
}
