package app.adapters.medicalRecord.repository;

import app.adapters.medicalRecord.entity.MedicalRecordEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {
    List<MedicalRecordEntity> findByPetId(Long petId);
    MedicalRecordEntity findByDateAndPetId(LocalDate date, long petId);
    List<MedicalRecordEntity> findByDiagnosisContaining(String diagnosis);
    List<MedicalRecordEntity> findByVeterinarianDocumentId(Long veterinarianId);

    @Modifying
    @Transactional
    @Query("UPDATE MedicalRecordEntity m SET m.orderCancelled = :cancelled WHERE m.orderId = :orderId")
    void updateOrderCancelledByOrderId(Long orderId, boolean cancelled);
}
