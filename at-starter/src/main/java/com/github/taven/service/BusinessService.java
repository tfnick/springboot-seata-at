package com.github.taven.service;

import com.github.taven.feign.OrderFeignService;
import com.github.taven.feign.ProductFeignService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BusinessService {

    @Resource
    private OrderFeignService orderFeignService;
    @Resource
    private ProductFeignService productFeignService;

    @GlobalTransactional(rollbackFor = Exception.class)
    public void buy(Integer goodsId, Integer quantity, boolean testRollback, boolean isSleep) {
        String xid = RootContext.getXID();
        log.info("purchase begin ... xid: " + xid);
        productFeignService.deduct(goodsId, quantity);
        orderFeignService.createOrder(goodsId, quantity, testRollback);
        // 猜测 GlobalTransactional 方法全部结束后才会结束第一阶段，在这里Sleep 进行验证
        if (isSleep) {
            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
/*

    @Autowired
    private RestTemplate restTemplate;

    private final RpcClient rpcClient = new RpcClient();
    public class RpcClient {
        private RpcClient() {
        }

        public void createOrder(String xid, Integer goodsId, Integer quantity, boolean testRollback) {
            String url = String.format("http://127.0.0.1:8001/order/createOrder?xid=%s&goodsId=%s&quantity=%s&testRollback=%s",
                    xid, goodsId, quantity, testRollback);
            try {
                restTemplate.getForEntity(url, Void.class);
            } catch (Exception e) {
                log.error("create url {} ,error:", url);
                throw new RuntimeException(e);
            }
        }

        public void deduct(String xid, Integer goodsId, Integer quantity) {
            String url = String.format("http://127.0.0.1:8002/product/deduct?xid=%s&goodsId=%s&quantity=%s", xid, goodsId, quantity);
            try {
                restTemplate.getForEntity(url, Void.class);
            } catch (Exception e) {
                log.error("create url {} ,error:", url);
                throw new RuntimeException(e);
            }
        }


    }
*/
}
