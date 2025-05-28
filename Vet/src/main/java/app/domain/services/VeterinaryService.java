package app.domain.services;

import com.app.domain.models.MedicalRecord;
import com.app.domain.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinaryService {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private OrderService orderService;

    public MedicalRecord recordMedicalVisit(MedicalRecord medicalRecord, Long userDocumentId) {
        MedicalRecord createdRecord = medicalRecordService.createMedicalRecord(medicalRecord, userDocumentId);
        if(medicalRecord.getMedication() != null && !medicalRecord.getMedication().isEmpty()) {
            Order order = new Order();
            order.setPetId(medicalRecord.getPetId());
            order.setVeterinarianDocumentId(medicalRecord.getVeterinarianDocumentId());
            order.setOwnerDocumentId(0);
            order.setMedicationName(medicalRecord.getMedication());
            order.setMedicationDose(medicalRecord.getMedicationDosage());
            Order createdOrder = medicalRecordService.createOrder(order, userDocumentId);
            createdRecord.setOrderId(createdOrder.getOrderId());
            medicalRecordService.updateMedicalRecord(createdRecord, userDocumentId);
        }
        return createdRecord;
    }

    public List<MedicalRecord> getPetMedicalHistory(Long petId, Long userDocumentId) {
        return  medicalRecordService.getMedicalRecordsByPetId(petId, userDocumentId);
    }

    public void cancelMedicationOrder(Long orderId, Long userDocumentId) {
        medicalRecordService.cancelOrder(orderId, userDocumentId);
    }
}
