package org.mkt.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.mkt.order.dto.OrderRequest;
import org.mkt.order.dto.OrderResponse;
import org.mkt.order.dto.Response;
import org.mkt.order.mapper.OrderMapper;
import org.mkt.order.model.Order;
import org.mkt.order.repository.OrderRepository;
import org.mkt.order.service.OrderService;
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
