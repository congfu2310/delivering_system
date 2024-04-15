package com.group.deliveringsystem.service;

import com.group.deliveringsystem.entity.Order;
import com.group.deliveringsystem.entity.OrderStatus;
import com.group.deliveringsystem.entity.User;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final String COLLECTION_NAME = "Order";
    @Resource
    private MongoTemplate mongoTemplate;

    public Order addOrder(Order order) {
        System.out.println(OrderStatus.PLACED.getName());
        Order newOrder = new Order();
        BeanUtils.copyProperties(order, newOrder);
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderStatus(OrderStatus.PLACED.getName());
        order.setCreatedTime(new Date());
        Order result = mongoTemplate.insert(newOrder, COLLECTION_NAME);
        return result;
    }

    /**
     * 用于更新出新的status
     * @param orderId
     * @param previousStatus
     * @param newStatus
     * @return
     */
    public Order updateState(String orderId, String previousStatus, OrderStatus newStatus) {
        Criteria criteriaId = Criteria.where("orderId").is(orderId);
        Criteria criteriaPreStatus = Criteria.where("orderStatus").is(previousStatus);
        Criteria joinedCriteria = new Criteria().andOperator(criteriaId, criteriaPreStatus);
        Query query = new Query(joinedCriteria);
        List<Order> results = mongoTemplate.find(query, Order.class, COLLECTION_NAME);

        // instead update, we add a new status
        Order newOrder = new Order();
        BeanUtils.copyProperties(results.get(0), newOrder);
        newOrder.setOrderStatus(newStatus.name());
        newOrder.setCreatedTime(new Date());
        Order result = addOrder(newOrder);
        return result;
    }

    public List<Order> findOrderHistory(String orderId){
        Criteria criteriaId = Criteria.where("orderId").is(orderId);
        Query query = new Query(criteriaId);
        List<Order> orderHistory = mongoTemplate.find(query, Order.class, COLLECTION_NAME);
        return orderHistory;
    }

    public List<Order> findAllOrder(String userId){
        Criteria criteriaId = Criteria.where("userId").is(userId);
        Query query = new Query(criteriaId);
        List<Order> results = mongoTemplate.find(query, Order.class, COLLECTION_NAME);
        Map<String, List<Order>> allOrders = results.stream().collect(Collectors.groupingBy(Order::getOrderId));

        List<Order> orders = new ArrayList<>();

        for (String orderId : allOrders.keySet()){
            List<Order> orderHistory = allOrders.get(orderId);
            Collections.sort(orderHistory, (o1, o2) -> o2.getCreatedTime().compareTo(o1.getCreatedTime()));
            orders.add(orderHistory.get(0));
        }

        return orders;

    }
}
