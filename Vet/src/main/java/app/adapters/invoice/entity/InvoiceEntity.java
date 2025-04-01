package app.adapters.invoice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class InvoiceEntity {
    @Id
    @Column(name = "invoice_id", unique = true)
    private long invoiceId;

    @Column(name = "pet_id", nullable = false)
    private long petId;

    @Column(name = "owner_id", nullable = false)
    private long ownerId;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}
