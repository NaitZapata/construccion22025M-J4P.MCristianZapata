package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class MedicalRecord {
    private LocalDate date;
    private Long petId;
    private long veterinarianDocumentId;
    private String consultationReason;
    private String symptoms;
    private String diagnosis;
    private String procedure;
    private String medication;
    private String medicationDosage;
    private long orderId;
    private List<String> vaccinationHistory;
    private List<String> medicationAllergies;
    private String procedureDetails;
    private boolean orderCancelled;
}
