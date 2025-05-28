package app.adapters.order;

import app.adapters.order.entity.OrderEntity;
import app.adapters.order.repository.OrderRepository;
import app.domain.models.Order;
import app.ports.OrderPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Service
public class OrderAdapter implements OrderPort {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean existOrder(long orderId) {
        return orderRepository.existsByOrderId(orderId);
    }

    @Override
    public void saveOrder(Order order) {
        OrderEntity orderEntity = orderAdapter(order);
        orderRepository.save(orderEntity);
        order.setOrderId(order.getOrderId());
    }

    @Override
    public Order findByOrderId(long orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        return orderAdapter(orderEntity);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream()
                .map(this::orderAdapter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllByPetId(long petId) {
        return orderRepository.findAll().stream()
                .filter(entity -> entity.getPetId() == petId)
                .map(this::orderAdapter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllByVeterinarianId(long veterinarianId) {
        return orderRepository.findAll().stream()
                .filter(entity -> entity.getVeterinarianDocumentId() == veterinarianId)
                .map(this::orderAdapter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllByNotCancelled() {
        return orderRepository.findAll().stream()
                .filter(entity -> !entity.getCancelled())
                .map(this::orderAdapter)
                .collect(Collectors.toList());
    }

    private Order orderAdapter(OrderEntity orderEntity) {
        if(orderEntity == null) {
            return null;
        }

        return new Order(
                orderEntity.getOrderId(),
                orderEntity.getPetId(),
                orderEntity.getOwnerDocumentId(),
                orderEntity.getVeterinarianDocumentId(),
                orderEntity.getMedicationName(),
                orderEntity.getMedicationDose(),
                orderEntity.getGenerationDate(),
                orderEntity.getCancelled()
        );
    }

    private OrderEntity orderAdapter(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setPetId(order.getPetId());
        orderEntity.setOwnerDocumentId(order.getOwnerDocumentId());
        orderEntity.setVeterinarianDocumentId(order.getVeterinarianDocumentId());
        orderEntity.setMedicationName(order.getMedicationName());
        orderEntity.setMedicationDose(order.getMedicationDose());
        orderEntity.setGenerationDate(order.getGenerationDate());
        orderEntity.setCancelled(order.isCancelled());
        return orderEntity;
    }
}
