package com.wang.controller;

import com.wang.service.JedisClientService;
import com.wang.service.RateLimiterService;
import com.wang.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * API访问速率控制测试
 */
@Controller
public class APIRateController {
    private int i;
    private final Semaphore permit = new Semaphore(10, true);

    @Autowired
    private RateLimiterService rateLimiterService;
    @Autowired
    private JedisClientService jedisClientService;

    @RequestMapping(value="/semapi",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> process() {

        Map<String,Object> map = new HashMap<String, Object>();
        try {
            permit.acquire();
            // 业务逻辑处理
            //long start=System.currentTimeMillis();
            System.out.println("============================================================"+i+"调用");
            map.put("code",0);
            map.put("msg","ok");
            Thread.sleep(5000);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",0);
            map.put("msg","ok");
            return map;
        } finally {
            System.out.println(i+"============================================================释放");
            permit.release();
            i++;
        }
    }

    @RequestMapping(value="/redisapi",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> redisapi(String uid) {
        System.out.println("==========================================="+uid);
        if (!rateLimiterService.acquire("limiter:" + uid, 1, System.currentTimeMillis())) {
            throw new RuntimeException("操作太过频繁，请休息一会");
        }

        Map<String,Object> map = new HashMap<String, Object>();
        try {

            // 业务逻辑处理
            //long start=System.currentTimeMillis();
            System.out.println("============================================================"+i+"调用");
            map.put("code",0);
            map.put("msg","ok");

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",0);
            map.put("msg","ok");
            return map;
        } finally {
            System.out.println(i+"============================================================释放");
            i++;
        }
    }




}
