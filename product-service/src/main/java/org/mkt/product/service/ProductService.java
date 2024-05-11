package org.mkt.product.service;

import org.mkt.product.dto.ProductRequest;
import org.mkt.product.dto.ProductResponse;
import org.mkt.product.dto.Response;

public interface ProductService extends IBaseService<ProductRequest, Response<ProductResponse>>{
}
