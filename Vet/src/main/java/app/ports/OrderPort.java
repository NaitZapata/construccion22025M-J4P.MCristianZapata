package app.ports;

import app.domain.models.Order;

import java.util.List;

public interface OrderPort {
    boolean existOrder(long orderId);
    void saveOrder(Order oder);
    Order findByOrderId(long orderId);
    List<Order> findAll();
    List<Order> findAllByPetId(long petId);
    List<Order> findAllByVeterinarianId(long veterinarianId);
    List<Order> findAllByNotCancelled();
}
