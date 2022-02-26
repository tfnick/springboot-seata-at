package com.github.taven.controller;

import com.github.taven.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/createOrder")
    public void createOrder(Integer goodsId, Integer quantity, boolean testRollback) {
        //log.info("createOrder param xid is {}", xid);
        log.info("createOrder Header xid is {}", RootContext.getXID());
        //RootContext.bind(xid); //不好的设计
        orderService.createOrder(goodsId, quantity, testRollback);
    }

}
