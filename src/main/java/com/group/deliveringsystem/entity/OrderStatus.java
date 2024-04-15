package com.group.deliveringsystem.entity;

public enum OrderStatus {

    PLACED("PLACED"),
    ACCEPTED_BY_RES("ACCEPTED_BY_RES"),
    SHIPPING("SHIPPING"),
    ACCEPTED_BY_CUSTOMER("ACCEPTED_BY_CUSTOMER"),
    REJECTED_BY_CUSTOMER("REJECTED_BY_CUSTOMER"),
    DELAY("DELAY"),
    CANCEl("CANCEl");

    private String name;

    private OrderStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
