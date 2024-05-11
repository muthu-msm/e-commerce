package org.mkt.product.service;

import org.mkt.common.dto.ProductRequest;
import org.mkt.common.dto.ProductResponse;
import org.mkt.common.dto.Response;

public interface ProductService extends IBaseService<ProductRequest, Response<ProductResponse>>{
}
