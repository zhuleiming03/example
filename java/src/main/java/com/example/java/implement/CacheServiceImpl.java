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

    public Integer getTryCatchException(Integer id) {
        try {
            int result = id + 1;
            System.out.println("try: 实际应该返回结果：" + result);
            return result;
        } catch (NullPointerException e) {
            System.out.println("catch: " + e);
        } finally {
            System.out.println("finally: 返回结果：0");
            return 0;
        }
    }
}
