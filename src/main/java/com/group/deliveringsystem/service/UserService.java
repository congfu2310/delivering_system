package com.group.deliveringsystem.service;

import com.group.deliveringsystem.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "User";
    @Resource
    private MongoTemplate mongoTemplate;

    public User addUser(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
         newUser.setUserType(user.getUserType());
        newUser.setId(UUID.randomUUID().toString());
        User newUserInfo = mongoTemplate.insert(user, COLLECTION_NAME);

        return newUserInfo;
    }


    public User getUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Criteria criteriaUsername = Criteria.where("username").is(username);
        Criteria criteriaPassword = Criteria.where("password").is(password);
        Criteria criteria = new Criteria().andOperator(criteriaUsername, criteriaPassword);
        Query query = new Query(criteria);
        List<User> documentList = mongoTemplate.find(query, User.class, COLLECTION_NAME);
        return user;
    }

    public String updateUser(User user) {
        Criteria criteria = Criteria.where("id").gt(user.getId());
        Query query = new Query(criteria);
        Update update = new Update().set("password", user.getPassword());
        UpdateResult result = mongoTemplate.updateMulti(query, update, User.class, COLLECTION_NAME);
        String resultInfo = "Match totally" + result.getMatchedCount() + "data, modify" + result.getModifiedCount() + "data";
        return resultInfo;
    }

    public String deleteUser(String id) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = new Query(criteria);
        DeleteResult result = mongoTemplate.remove(query, COLLECTION_NAME);
        String resultInfo = "Delete successfully " + result.getDeletedCount() + " data";
        return resultInfo;
    }


}
