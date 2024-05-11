package org.mkt.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.ProductRequest;
import org.mkt.common.dto.ProductResponse;
import org.mkt.common.dto.Response;
import org.mkt.product.mapper.ProductMapper;
import org.mkt.product.model.Product;
import org.mkt.product.repository.ProductRepository;
import org.mkt.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Response<ProductResponse> create(ProductRequest request) {
        request.setId(null);
        Product product = productRepository.save(ProductMapper.INSTANCE.requestToEntity(request));

        return new Response<>(1, ProductMapper.INSTANCE.entityToRequest(product));
    }

    @Override
    public Response<ProductResponse> update(ProductRequest request) {
        Optional<Product> product = productRepository.findById(request.getId());

        return product.map(value -> {
                    return new Response<>(1, ProductMapper.INSTANCE.entityToRequest(productRepository.save(ProductMapper.INSTANCE.requestToEntity(request))));
                })
                .orElseGet(() -> new Response<>(404, "Not found"));
    }

    @Override
    public Response<Long> delete(Long id) {
        Optional<Product> product = productRepository.findById(id);

        return product.map(value -> {
                    product.get().setStatus("D");
                    productRepository.save(product.get());
                    return new Response<>(1, id);
                })
                .orElseGet(() -> new Response<>(404, "Not found"));
    }

    @Override
    public Response<ProductResponse> get(Long id) {
        Optional<Product> product = productRepository.findById(id);

        return product.map(value -> new Response<>(1, ProductMapper.INSTANCE.entityToRequest(value)))
                .orElseGet(() -> new Response<>(404, "Not found"));

    }
}
