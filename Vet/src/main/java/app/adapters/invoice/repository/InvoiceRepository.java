package app.adapters.invoice.repository;

import app.adapters.invoice.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    List<InvoiceEntity> findByPetId(long petId);
    List<InvoiceEntity> findByOwnerId(long ownerId);
    List<InvoiceEntity> findByOrderId(long orderId);
    List<InvoiceEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
