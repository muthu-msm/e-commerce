package org.mkt.product.controller;


import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.ProductRequest;
import org.mkt.common.dto.ProductResponse;
import org.mkt.common.dto.Response;
import org.mkt.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Response<ProductResponse>> create(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.create(request));
    }

    @PutMapping
    public ResponseEntity<Response<ProductResponse>> update(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ProductResponse>> get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(productService.get(id));
    }

}
