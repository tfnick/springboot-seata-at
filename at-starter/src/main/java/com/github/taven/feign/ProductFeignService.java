package com.github.taven.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-product-service")
public interface ProductFeignService {
    @GetMapping("/product/deduct")
    void deduct(@RequestParam Integer goodsId, @RequestParam Integer quantity);
}
