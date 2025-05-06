package com.example.order.feign;

import com.example.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 远程调用注册中心服务
 *
 */
@FeignClient("service-product")
public interface ProductFeignClient {

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable("id") Long id);

}
