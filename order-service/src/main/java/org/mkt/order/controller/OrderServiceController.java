package org.mkt.order.controller;


import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.OrderRequest;
import org.mkt.common.dto.OrderResponse;
import org.mkt.common.dto.Response;
import org.mkt.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderServiceController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Response<OrderResponse>> create(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.create(request));
    }

    @PutMapping
    public ResponseEntity<Response<OrderResponse>> update(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<OrderResponse>> create(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.get(id));
    }

}
