package org.mkt.order.service;

import org.mkt.common.dto.OrderRequest;
import org.mkt.common.dto.OrderResponse;
import org.mkt.common.dto.Response;

public interface OrderService extends IBaseService<OrderRequest, Response<OrderResponse>>{
}
