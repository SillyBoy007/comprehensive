package com.wang.controller;

import com.wang.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MongoController {
    @Autowired
    private MongoDbService mongoDbService;

    @RequestMapping("/mongo")
    @ResponseBody
    public void mongoController(){
        mongoDbService.mongoTest();
    }

}
