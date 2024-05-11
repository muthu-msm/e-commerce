package org.mkt.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mkt.user.dto.OrderRequest;
import org.mkt.user.dto.OrderResponse;
import org.mkt.user.model.Order;

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
