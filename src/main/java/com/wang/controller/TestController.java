package com.wang.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping()
    public String getPage(){
        return "test/promise";
    }
    @RequestMapping("/getData")
    @ResponseBody
    public JSONObject testData(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","wang");
        return jsonObject;
    }
}
