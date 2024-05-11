package org.mkt.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.mkt.user.dto.OrderRequest;
import org.mkt.user.dto.OrderResponse;
import org.mkt.user.dto.Response;
import org.mkt.user.mapper.OrderMapper;
import org.mkt.user.model.Order;
import org.mkt.user.repository.OrderRepository;
import org.mkt.user.service.OrderService;
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
