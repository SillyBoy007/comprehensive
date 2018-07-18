package com.wang.service.Impl;

import com.wang.service.RateLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateLimiterServiceImpl implements RateLimiterService {


    @Autowired
    private RedisTemplate redisTemplate;
    @SuppressWarnings("unchecked")
    public boolean acquire(String key, Integer permits, long currMillSecond,String max_permits,String rateStr) {
        try  {
            //针对新用户创建令牌桶
            if (!redisTemplate.hasKey(key)) {
                redisTemplate.opsForHash().put(key, "last_mill_second", String.valueOf(currMillSecond));
                redisTemplate.opsForHash().put(key, "curr_permits", "0");
                redisTemplate.opsForHash().put(key, "max_permits", max_permits);
                redisTemplate.opsForHash().put(key, "rate",rateStr);
                return true;
            }
            //获取令牌桶信息，上一个令牌时间，当前可用令牌数，最大令牌数，令牌消耗速率
            List<Object> keys = new ArrayList<Object>();
            keys.add("last_mill_second");
            keys.add("curr_permits");
            keys.add("max_permits");
            keys.add("rate");
            List<Object> strings = redisTemplate.opsForHash().multiGet(key,keys);
            long lastMillSecond = Long.parseLong(strings.get(0).toString());
            Integer currPermits = Integer.valueOf(strings.get(1).toString());
            Integer maxPermits = Integer.valueOf(strings.get(2).toString());
            Double rate = Double.valueOf(strings.get(3).toString());
            //向桶里面添加令牌
            Double reversePermitsDouble = ((currMillSecond - lastMillSecond) / 1000) * rate;

            Integer reversePermits = reversePermitsDouble.intValue();
            Integer expectCurrPermits = reversePermits + currPermits;
            Integer localCurrPermits = Math.min(expectCurrPermits, maxPermits);
            //添加令牌之后更新时间
            if (reversePermits > 0) {
                redisTemplate.opsForHash().put(key, "last_mill_second", String.valueOf(currMillSecond));
            }
            //判断桶里面剩余的令牌数目
            if (localCurrPermits - permits >= 0) {
                redisTemplate.opsForHash().put(key, "curr_permits", String.valueOf(localCurrPermits - permits));
                return true;
            } else {

                redisTemplate.opsForHash().put(key, "curr_permits", String.valueOf(localCurrPermits));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
