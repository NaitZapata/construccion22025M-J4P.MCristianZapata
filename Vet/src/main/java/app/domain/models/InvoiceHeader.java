package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor

public class InvoiceHeader {
    private long id;
    private long petId;
    private long ownerId;
    private long orderId;
    private char itmes;
    private double value;
    private double amount;
    private Timestamp dateCreated;

    public InvoiceHeader(long id, long petId, long ownerId, long orderId, char itmes, double value, double amount, Timestamp dateCreated) {
        this.id = id;
        this.petId = petId;
        this.ownerId = ownerId;
        this.orderId = orderId;
        this.itmes = itmes;
        this.value = value;
        this.amount = amount;
        this.dateCreated = dateCreated;
    }
}
