package app.adapters.order.entity;

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
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "order_id", unique = true)
    private long orderId;

    @Column(name = "pet_id", nullable = false)
    private long petId;

    @Column(name = "veterinarian_document_id", nullable = false)
    private long veterinarianDocumentId;

    @Column(name = "owner_document_id", nullable = false)
    private long ownerDocumentId;

    @Column(name = "medication_name")
    private String medicationName;

    @Column(name = "medication_dose")
    private String medicationDose;

    @Column(name = "generation_date")
    private LocalDate generationDate;

    @Column(name = "cancelled")
    private Boolean cancelled;

}
