package com.group.deliveringsystem.controller;


import com.group.deliveringsystem.entity.Order;
import com.group.deliveringsystem.entity.OrderStatus;
import com.group.deliveringsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {


    @Autowired
    OrderService orderService;


    @GetMapping("/order/{orderId}")
    public List<Order> getOrderHistory(@PathVariable String orderId){
        return orderService.findOrderHistory(orderId);
    }

    @GetMapping("/orders/{userId}")
    public List<Order> getAllOrdersByUserId(@PathVariable String userId){
        return orderService.findAllOrder(userId);
    }

    @PutMapping("/order")
    public void updateOrderStatus(String orderId, String previousStatus, OrderStatus newStatus){
        orderService.updateState(orderId, previousStatus, newStatus);
    }

    @PostMapping("/order")
    public List<Order> addOrder(Order order){
        String userId = order.getUserId();
        orderService.addOrder(order);
        return orderService.findAllOrder(userId);
    }
}
