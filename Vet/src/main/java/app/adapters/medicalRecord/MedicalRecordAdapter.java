package app.adapters.medicalRecord;

import app.adapters.medicalRecord.entity.MedicalRecordEntity;
import app.adapters.medicalRecord.repository.MedicalRecordRepository;
import app.domain.models.MedicalRecord;
import app.ports.MedicalRecordPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRecordAdapter implements MedicalRecordPort {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecordEntity entity = toEntity(medicalRecord);
        entity = medicalRecordRepository.save(entity);
        return toModel(entity);
    }

    @Override
    public List<MedicalRecord> findByPetId(Long petId) {
        return medicalRecordRepository.findByPetId(petId)
                .stream().map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecord> findByDiagnosisContaining(String diagnosis) {
        return medicalRecordRepository.findByDiagnosisContaining(diagnosis)
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecord findByDateAndPetId(LocalDate date, Long petId) {
        MedicalRecordEntity entity = medicalRecordRepository.findByDateAndPetId(date, petId);
        return entity != null ? toModel(entity) : null;
    }

    @Override
    public List<MedicalRecord> findByVeterinarianDocumentId(Long veterinarianId) {
        return medicalRecordRepository.findByVeterinarianDocumentId(veterinarianId)
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrderCancelled(Long orderId, boolean cancelled) {
        medicalRecordRepository.updateOrderCancelledByOrderId(orderId, cancelled);
    }

    private MedicalRecord toModel(MedicalRecordEntity entity) {
        MedicalRecord model = new MedicalRecord();
        model.setDate(entity.getDate());
        model.setPetId(entity.getPetId());
        model.setVeterinarianDocumentId(entity.getVeterinarianDocumentId());
        model.setConsultationReason(entity.getConsultationReason());
        model.setSymptoms(entity.getSymptoms());
        model.setDiagnosis(entity.getDiagnosis());
        model.setProcedure(entity.getProcedure());
        model.setMedication(entity.getMedication());
        model.setMedicationDosage(entity.getMedicationDosage());
        model.setOrderId(Long.parseLong(String.valueOf(entity.getOrderId())));
        model.setVaccinationHistory(entity.getVaccinationHistory());
        model.setMedicationAllergies(entity.getMedicationAllergies());
        model.setProcedureDetails(entity.getProcedureDetails());
        model.setOrderCancelled(entity.isOrderCancelled());
        return model;
    }

    private MedicalRecordEntity toEntity(MedicalRecord model) {
        MedicalRecordEntity entity = new MedicalRecordEntity();
        entity.setDate(model.getDate());
        entity.setPetId(model.getPetId());
        entity.setVeterinarianDocumentId(model.getVeterinarianDocumentId());
        entity.setConsultationReason(entity.getConsultationReason());
        entity.setSymptoms(entity.getSymptoms());
        entity.setDiagnosis(entity.getDiagnosis());
        entity.setProcedure(entity.getProcedure());
        entity.setMedication(entity.getMedication());
        entity.setMedicationDosage(entity.getMedicationDosage());
        entity.setOrderId(Long.parseLong(String.valueOf(entity.getOrderId())));
        entity.setVaccinationHistory(entity.getVaccinationHistory());
        entity.setMedicationAllergies(entity.getMedicationAllergies());
        entity.setProcedureDetails(entity.getProcedureDetails());
        entity.setOrderCancelled(entity.isOrderCancelled());
        return entity;
    }
}
