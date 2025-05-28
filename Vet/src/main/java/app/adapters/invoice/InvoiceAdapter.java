package app.adapters.invoice;

import app.adapters.invoice.entity.InvoiceEntity;
import app.adapters.invoice.repository.InvoiceRepository;
import app.domain.models.Invoice;
import app.ports.InvoicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceAdapter implements InvoicePort {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        InvoiceEntity entity = toEntity(invoice);
        entity = invoiceRepository.save(entity);
        invoice.setInvoiceId(entity.getInvoiceId());
        return invoice;
    }

    @Override
    public Invoice findByInvoiceId(long invoiceId) {
        InvoiceEntity entity = invoiceRepository.findById(invoiceId).orElse(null);
        return entity != null ? toModel(entity) : null;
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findByPetId(long petId) {
        return invoiceRepository.findByPetId(petId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findByOwnerId(long ownerId) {
        return invoiceRepository.findByOwnerId(ownerId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findByOrderId(long orderId) {
        return invoiceRepository.findByOrderId(orderId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        return invoiceRepository.findByDateBetween(startDate, endDate).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private Invoice toModel(InvoiceEntity entity) {
        Invoice model = new Invoice();
        model.setInvoiceId(entity.getInvoiceId());
        model.setPetId(entity.getPetId());
        model.setOwnerId(entity.getOwnerId());
        model.setOrderId(entity.getOrderId());
        model.setProductName(entity.getProductName());
        model.setValue(entity.getValue());
        model.setQuantity(entity.getQuantity());
        model.setDate(entity.getDate());
        return model;
    }

    private InvoiceEntity toEntity(Invoice model) {
        InvoiceEntity entity = new InvoiceEntity();
        entity.setInvoiceId(entity.getInvoiceId());
        entity.setPetId(entity.getPetId());
        entity.setOwnerId(entity.getOwnerId());
        entity.setOrderId(entity.getOrderId());
        entity.setProductName(entity.getProductName());
        entity.setValue(entity.getValue());
        entity.setQuantity(entity.getQuantity());
        entity.setDate(entity.getDate());
        return entity;
    }
}
