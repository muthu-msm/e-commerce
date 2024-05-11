package org.mkt.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mkt.manager.dto.OrderRequest;
import org.mkt.manager.dto.OrderResponse;
import org.mkt.manager.model.Order;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderID", target = "id")
    @Mapping(source = "orderNumber", target = "number")
    Order requestToEntity(OrderRequest request);

    @Mapping(source = "id", target = "orderID")
    @Mapping(source = "number", target = "orderNumber")
    OrderResponse entityToRequest(Order request);
}
