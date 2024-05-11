package org.mkt.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.mkt.manager.dto.OrderRequest;
import org.mkt.manager.dto.OrderResponse;
import org.mkt.manager.dto.Response;
import org.mkt.manager.mapper.OrderMapper;
import org.mkt.manager.model.Order;
import org.mkt.manager.repository.OrderRepository;
import org.mkt.manager.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public Response<OrderResponse> create(OrderRequest request) {
        request.setOrderID(null);
        Order order = orderRepository.save(OrderMapper.INSTANCE.requestToEntity(request));

        return new Response<>(1, OrderMapper.INSTANCE.entityToRequest(order));
    }

    @Override
    public Response<OrderResponse> update(OrderRequest request) {
        Order order = orderRepository.save(OrderMapper.INSTANCE.requestToEntity(request));
        return new Response<>(1, OrderMapper.INSTANCE.entityToRequest(order));
    }

    @Override
    public Long delete(Long id) {
        return 0L;
    }

    @Override
    public Response<OrderResponse> get(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        return order.map(value -> new Response<>(1, OrderMapper.INSTANCE.entityToRequest(value)))
                .orElseGet(() -> new Response<>(404, "Not found", null));

    }
}
