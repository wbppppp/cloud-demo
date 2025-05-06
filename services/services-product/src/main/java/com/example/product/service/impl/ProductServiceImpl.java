package com.example.product.service.impl;

import com.example.product.Product;
import com.example.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal(productId));
        product.setProductName("苹果"+ productId);
        product.setNum(2);
        return product;
    }
}
