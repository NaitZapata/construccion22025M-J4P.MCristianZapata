package app.adapters.order.repository;

import app.adapters.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    boolean existsByOrderId(long orderId);
    OrderEntity findByOrderId(long orderId);
}
