package app.domain.services;

import com.app.domain.models.Order;
import com.app.ports.OrderPort;
import com.app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService{
    @Autowired
    private OrderPort orderPort;

    @Autowired
    private PersonPort personPort;

    public Order createOrder(Order order) {
        validateOrder(order);
        orderPort.saveOrder(order);
        return order;
    }

    public Order updateOrder(Order order) {
        if(!orderPort.existOrder(order.getOrderId())) {
            throw new RuntimeException("The order does not exist.");
        }

        orderPort.saveOrder(order);
        return order;
    }

    public Order getOrderById(Long orderId) {
        Order order = orderPort.findByOrderId(orderId);
        if(order == null) {
            throw new IllegalArgumentException("Order not found whit ID: " + orderId);
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return orderPort.findAll();
    }

    public List<Order> getOrdersByPetId(long petId) {
        return orderPort.findAllByPetId(petId);
    }

    public List<Order> getOrdersByVeterinarianId(long veterinarianId) {
        return orderPort.findAllByVeterinarianId(veterinarianId);
    }

    public boolean checkOrderAccess(Long orderId, Long userDocumentId) {
        var person = personPort.findByDocument(userDocumentId);
        if(person == null) {
            return false;
        }

        String role = person.getRole();
        return "VETERINARIAN".equalsIgnoreCase(role) || "SELLER".equalsIgnoreCase(role);
    }

    private void validateOrder(Order order) {
        if(order.getPetId() <= 0) {
            throw new IllegalArgumentException("Invalid pet ID.");
        }

        if(order.getOwnerDocumentId() <= 0) {
            throw new IllegalArgumentException("Invalid owner ID.");
        }

        if(order.getVeterinarianDocumentId() <= 0) {
            throw new IllegalArgumentException("Invalid vet ID");
        }
    }
}
