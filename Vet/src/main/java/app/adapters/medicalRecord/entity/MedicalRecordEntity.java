package app.adapters.medicalRecord.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "medical_records")
public class MedicalRecordEntity {
    @Id
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "pet_id", nullable = false)
    private long petId;

    @Column(name = "veterinarian_document_id", nullable = false)
    private long veterinarianDocumentId;

    @Column(name = "consultation_reason")
    private String consultationReason;

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "procedure")
    private String procedure;

    @Column(name = "medication")
    private String medication;

    @Column(name = "medication_dosage")
    private String medicationDosage;

    @Column(name = "order_id", unique = true)
    private long orderId;

    @ElementCollection
    @CollectionTable(name = "medica_record_vaccination_history",
            joinColumns = @JoinColumn(name = "medical_record_date"))
    @Column(name = "vaccination_history")
    private List<String> vaccinationHistory;

    @ElementCollection
    @CollectionTable(name = "medical_record_medication_allergies")
    @Column(name = "medication_allergies")
    private List<String> medicationAllergies;

    @Column(name = "procedure_details")
    private String procedureDetails;

    @Column(name = "order_cancelled")
    private boolean orderCancelled;
}
