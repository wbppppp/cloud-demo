package com.example.order.feign.fallback;
import java.math.BigDecimal;

import com.example.order.feign.ProductFeignClient;
import com.example.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductFeignClientFallback implements ProductFeignClient {

    @Override
    public Product getProduct(Long id) {
        Product product = new Product();
        product.setId(0L);
        product.setPrice(new BigDecimal("0"));
        product.setProductName("我是假的");
        product.setNum(0);
        return product;
    }
}
