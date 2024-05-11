package org.mkt.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order")
public interface OrderFeignClient {

    @GetMapping("/api/resource")
    String getResource();
}