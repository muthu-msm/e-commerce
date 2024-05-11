package org.mkt.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mkt.product.dto.ProductRequest;
import org.mkt.product.dto.ProductResponse;
import org.mkt.product.model.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product requestToEntity(ProductRequest request);

    ProductResponse entityToRequest(Product request);
}