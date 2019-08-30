package com.example.java.service;



public interface CacheService {

    /**
     * 该方法过时，请调用 getAddUpResult
     * @param type
     * @param ids
     * @return
     */
    @Deprecated
    Integer getCacheQueryCount(String type, Integer... ids);

    Integer getAddUpResult(Integer... ids);
}
