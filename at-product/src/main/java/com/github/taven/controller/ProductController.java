package com.github.taven.controller;

import com.github.taven.service.ProductService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/deduct")
    public void deduct(Integer goodsId, Integer quantity) {
        //log.info("debuct param xid is {}", xid);
        log.info("debuct Header xid is {}", RootContext.getXID());

        //RootContext.bind(xid);//不好的设计
        productService.deduct(goodsId, quantity);
    }

}
