package app.ports;

import app.domain.models.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordPort {
    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);
    List<MedicalRecord> findByPetId(Long petId);
    List<MedicalRecord> findByDiagnosisContaining(String diagnosis);
    MedicalRecord findByDateAndPetId(LocalDate date, Long petId);
    List<MedicalRecord> findByVeterinarianDocumentId(Long veterinarianId);
    void updateOrderCancelled(Long orderId, boolean cancelled);
}
