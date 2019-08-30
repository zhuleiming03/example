package com.example.java.implement;

import com.example.java.service.CacheService;

public class CacheServiceImpl implements CacheService {

    public Integer getCacheQueryCount(String type, Integer... ids) {
        Integer result = 0;
        for (int i = 0; i < ids.length; i++) {
            result += ids[i];
        }
        return result;
    }

    public Integer getAddUpResult(Integer... ids) {
        Integer result = 0;
        for (int i = 0; i < ids.length; i++) {
            result += ids[i];
        }
        return result;
    }
}
