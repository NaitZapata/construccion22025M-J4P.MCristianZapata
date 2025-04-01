package app.ports;

import app.domain.models.Invoice;
import java.time.LocalDate;
import java.util.List;

public interface InvoicePort {
    Invoice saveInvoice(Invoice invoice);
    Invoice findByInvoiceId(long invoiceId);
    List<Invoice> findAll();
    List<Invoice> findByPetId(long petId);
    List<Invoice> findByOwnerId(long ownerId);
    List<Invoice> findByOrderId(long orderId);
    List<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
