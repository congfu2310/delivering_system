package com.group.deliveringsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @MongoId
    private String orderId;
    private String restId;


//    @Field("orderStatus")
    private String orderStatus;

    private String comment;

    private int price;

    private String userId;

    private String driverId;
    @CreatedDate
    private Date createdTime;
}
