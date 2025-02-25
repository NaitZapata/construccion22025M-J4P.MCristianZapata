package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class InvoiceDetail {
    private long InvoiceDetailId;
    private InvoiceHeader invoiceHeader;
    private char items;
    private double amount;

    public InvoiceDetail(long invoiceDetailId, InvoiceHeader invoiceHeader, char items, double amount) {
        InvoiceDetailId = invoiceDetailId;
        this.invoiceHeader = invoiceHeader;
        this.items = items;
        this.amount = amount;
    }
}
