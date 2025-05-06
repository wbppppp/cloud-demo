package com.example.order.feign;

import com.example.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 远程调用第三方api
 */
@FeignClient(value = "weather-client", url = "http://aliv18.data.moji.com")
public interface WeatherFeignClient {

    @PostMapping("/whapi/json/alicityweather/condition")
    String getProduct(@RequestHeader("Authorization") String auth, @RequestParam("token") String token, @RequestParam("cityId") String cityId);

}
