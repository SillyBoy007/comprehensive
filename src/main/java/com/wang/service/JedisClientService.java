package com.wang.service;

import java.util.List;

/**
 * 连接Redis客户端
 */
public interface JedisClientService {
    String set(String key, String value);
    String get(String key);
    Boolean exists(String key);
    //失效
    Long expire(String key, int seconds);
    Long ttl(String key);
    Long incr(String key);
    Long hset(String key, String field, String value);
    String hget(String key, String field);
    Long hdel(String key, String field);
    List<String> hmget(String var1, String... var2);

}
