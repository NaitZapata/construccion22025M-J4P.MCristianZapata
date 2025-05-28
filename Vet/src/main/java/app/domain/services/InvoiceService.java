package app.domain.services;

import com.app.domain.models.Invoice;
import com.app.domain.models.Order;
import com.app.ports.InvoicePort;
import com.app.ports.OrderPort;
import com.app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoicePort invoicePort;

    @Autowired
    private OrderPort orderPort;

    @Autowired
    private PersonPort personPort;

    public Invoice createInvoice(Invoice invoice, Long userDocumentId) {
        validateSeller(userDocumentId);

        if(invoice.getOrderId() > 0) {
            Order order = orderPort.findByOrderId(invoice.getOrderId());
            if(order == null) {
                throw new RuntimeException("The order does not exist.");
            }

            if(order.isCancelled()) {
                throw new RuntimeException("An invoice cannot be generated for a canceled order.");
            }

            invoice.setProductName(order.getMedicationName());
            invoice.setPetId(order.getPetId());
            invoice.setOwnerId(order.getOwnerDocumentId());
        }
        validateInvoice(invoice);

        if(invoice.getDate() == null) {
            invoice.setDate(LocalDate.now());
        }
        return invoicePort.saveInvoice(invoice);
    }

    public List<Invoice> getInvoicesByPetId(long petId) {
        return invoicePort.findByPetId(petId);
    }

    public List<Invoice> getInvoicesByOwnerId(long ownerId) {
        return invoicePort.findByOwnerId(ownerId);
    }

    public List<Invoice> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate) {
        return invoicePort.findByDateBetween(startDate, endDate);
    }

    private void validateSeller(Long userDocumentId) {
        var person = personPort.findByDocument(userDocumentId);
        if(person == null) {
            throw new RuntimeException("User not found.");
        }

        if(!"SELLER".equalsIgnoreCase(person.getRole())) {
            throw new RuntimeException("Only sellers can generate invoices.");
        }
    }

    private void validateInvoice(Invoice invoice) {
        if(invoice.getProductName() == null || invoice.getProductName().isEmpty()) {
            throw new IllegalArgumentException("The product name is required.");
        }

        if(invoice.getValue() <= 0) {
            throw new IllegalArgumentException("The value must be greater than zero.");
        }

        if(invoice.getQuantity() <= 0) {
            throw new IllegalArgumentException("The quantity must be greater than zero.");
        }
    }
}
