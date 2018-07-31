package com.wang.service.Impl;

import com.wang.entity.User;
import com.wang.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongoDbTest implements MongoDbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void mongoTest(){
        User user = new User();
        user.setUsername("xia");
        mongoTemplate.save(user);
    }

}
