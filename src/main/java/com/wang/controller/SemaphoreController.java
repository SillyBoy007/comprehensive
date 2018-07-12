package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

@Controller
public class SemaphoreController {
    private int i;
    private final Semaphore permit = new Semaphore(10, true);

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
}
