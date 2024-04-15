package com.group.deliveringsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeliveringSystemApplicationTests {

    @Autowired
    TestService testService;

    @Test
    void contextLoads() {
        System.out.println(testService.insert());
    }

}
