package com.example.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")//配置批量绑定在nacos下，可以无需使用@RefreshScope就能实现批量动态刷新
@Data
public class OrderProperties {

    private String timeout;

    private String autoConfirm;

    private String dbUrl;

}
