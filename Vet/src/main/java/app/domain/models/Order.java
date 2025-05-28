package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class Order {
    private long orderId;
    private long petId;
    private long ownerDocumentId;
    private long veterinarianDocumentId;
    private String medicationName;
    private String medicationDose;
    private LocalDate generationDate;
    private boolean cancelled;

    public Order(long orderId, long petId, long ownerDocumentId, long veterinarianDocumentId, String medicationName, String medicationDose, LocalDate generationDate, boolean cancelled) {
        this.orderId = orderId;
        this.petId = petId;
        this.ownerDocumentId = ownerDocumentId;
        this.veterinarianDocumentId = veterinarianDocumentId;
        this.medicationName = medicationName;
        this.medicationDose = medicationDose;
        this.generationDate = generationDate;
        this.cancelled = cancelled;
    }
}
