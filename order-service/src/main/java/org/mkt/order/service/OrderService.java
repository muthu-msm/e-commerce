package org.mkt.order.service;

import org.mkt.order.dto.OrderRequest;
import org.mkt.order.dto.OrderResponse;
import org.mkt.order.dto.Response;

public interface OrderService extends IBaseService<OrderRequest, Response<OrderResponse>>{
}
