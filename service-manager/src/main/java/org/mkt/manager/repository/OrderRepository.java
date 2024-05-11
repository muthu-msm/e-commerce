package org.mkt.manager.repository;

import org.mkt.manager.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
