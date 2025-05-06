package com.example.order.controller;

import com.example.order.Order;
import com.example.order.properties.OrderProperties;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RefreshScope//nacos配置自动刷新
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProperties orderProperties;



    @GetMapping("/config")
    public String getConfig() {
        return "orderTimeout = " + orderProperties.getTimeout() +"; autoConfirm = " + orderProperties.getAutoConfirm() +"; dbUrl = " + orderProperties.getDbUrl();
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
        return orderService.createOrder(userId, productId);
    }

}
