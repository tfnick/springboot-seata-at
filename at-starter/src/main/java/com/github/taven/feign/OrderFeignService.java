package com.github.taven.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-order-service")
public interface OrderFeignService {

    @GetMapping("/order/createOrder")
    void createOrder(@RequestParam Integer goodsId, @RequestParam Integer quantity, @RequestParam boolean testRollback);
}
