package org.mkt.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product")
public interface ProductServiceFeignClient {

    @GetMapping("/api/resource")
    String getResource();
}