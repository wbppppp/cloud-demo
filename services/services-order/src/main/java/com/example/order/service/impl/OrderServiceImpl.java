package com.example.order.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.example.order.Order;
import com.example.order.feign.ProductFeignClient;
import com.example.order.service.OrderService;
import com.example.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public Order createOrder(Long userId, Long productId) {

       // Product product = getProductFromRemoteAnnotation(productId);

        Product product = productFeignClient.getProduct(productId);

        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(product.getNum())));
        order.setUserId(userId);
        order.setNickName("niganma");
        order.setAddress("南京");
        order.setProductList(List.of(product));

        return order;
    }

    private Product getProductFromRemoteAnnotation(Long productId) {


        String url = "http://service-product/product/" + productId;
        log.info("product service url is {}", url);

        return restTemplate.getForObject(url, Product.class);

    }

    private Product getProductFromRemote(Long productId) {

        ServiceInstance choose = loadBalancerClient.choose("service-product");

        String url = "http://" + choose.getHost() + ":" + choose.getPort() +"/product/" + productId;
        log.info("product service url is {}", url);

        return restTemplate.getForObject(url, Product.class);

    }
}
