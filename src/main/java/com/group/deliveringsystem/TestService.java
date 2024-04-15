package com.group.deliveringsystem;

import com.group.deliveringsystem.entity.User;
import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@Service
@CrossOrigin

public class TestService {
    @Resource
    private MongoTemplate mongoTemplate;
    public User insert() {
        // 设置用户信息
        User user = User.builder().id(UUID.randomUUID().toString()).build();
        // 插入一条用户数据，如果文档信息已经存在就抛出异常
        User newUser = mongoTemplate.insert(user, "User");
        // 输出存储结果
        return newUser;
    }
}
