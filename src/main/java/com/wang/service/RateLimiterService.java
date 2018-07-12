package com.wang.service;

public interface RateLimiterService  {
    boolean acquire(String key, Integer permits, long currMillSecond);
}
