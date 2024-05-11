package org.mkt.user.service;

import org.mkt.user.dto.OrderRequest;
import org.mkt.user.dto.OrderResponse;
import org.mkt.user.dto.Response;

public interface OrderService extends IBaseService<OrderRequest, Response<OrderResponse>>{
}
