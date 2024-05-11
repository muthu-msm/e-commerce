package org.mkt.manager.service;

import org.mkt.manager.dto.OrderRequest;
import org.mkt.manager.dto.OrderResponse;
import org.mkt.manager.dto.Response;

public interface OrderService extends IBaseService<OrderRequest, Response<OrderResponse>>{
}
