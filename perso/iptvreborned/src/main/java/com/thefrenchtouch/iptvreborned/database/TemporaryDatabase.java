package com.thefrenchtouch.iptvreborned.database;


import java.util.HashMap;
import java.util.Map;

public class TemporaryDatabase {
    private Map<String,Object> map;
    public TemporaryDatabase(){
        map = new HashMap<>();
    }

    public boolean save(String key,Object value){
        this.map.put(key,value);
        return true;
    }
    public <T> T get(String key, Class<T> clazz){
        return (T) map.remove(key);
    }
}
