package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor

public class Invoice {
    private long invoiceId;
    private long petId;
    private long ownerId;
    private long orderId;
    private  String productName;
    private double value;
    private int quantity;
    private LocalDate date;
}
