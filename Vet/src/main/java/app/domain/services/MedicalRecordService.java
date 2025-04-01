package app.domain.services;

import com.app.domain.models.MedicalRecord;
import com.app.domain.models.Order;
import com.app.ports.MedicalRecordPort;
import com.app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordPort medicalRecordPort;

    @Autowired
    private PersonPort personPort;

    @Autowired
    private OrderService orderService;

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord, Long userDocumentId) {
        validateVeterinarian(userDocumentId);
        validateMedicalRecord(medicalRecord);
        return  medicalRecordPort.saveMedicalRecord(medicalRecord);
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, Long userDocumentId) {
        validateVeterinarian(userDocumentId);
        MedicalRecord existingRecord = medicalRecordPort.findByDateAndPetId(
                medicalRecord.getDate(), medicalRecord.getPetId());

        if(existingRecord == null) {
            throw new RuntimeException("The medical record does not exist.");
        }

        return medicalRecordPort.saveMedicalRecord(medicalRecord);
    }

    public List<MedicalRecord> getMedicalRecordsByPetId(Long petId, Long userDocumentId) {
        validateVeterinarian(userDocumentId);
        return medicalRecordPort.findByPetId(petId);
    }

    public Order createOrder(Order order, Long userDocumentId) {
        validateVeterinarian(userDocumentId);
        if(order.getMedicationName() == null || order.getMedicationName().isEmpty()) {
            throw new IllegalArgumentException("The name of the medication is required.");
        }

        if(order.getMedicationDose() == null || order.getMedicationDose().isEmpty()) {
            throw new IllegalArgumentException("The dosage medicine is required.");
        }

        if(order.getGenerationDate() == null) {
            order.setGenerationDate(LocalDate.now());
        }

        return orderService.createOrder(order);
    }

    public void cancelOrder(Long orderId, Long userDocumentId) {
        validateVeterinarian(userDocumentId);
        Order order = orderService.getOrderById(orderId);
        if(order == null) {
            throw new RuntimeException("The order does not exist.");
        }

        order.setCancelled(true);
        orderService.updateOrder(order);
        medicalRecordPort.updateOrderCancelled(orderId, true);
    }

    public void validateVeterinarian(Long userDocumentId) {
        var person = personPort.findByDocument(userDocumentId);
        if(person == null) {
            throw new RuntimeException("User not found.");
        }

        if(!"VETERINARIAN".equalsIgnoreCase(person.getRole())) {
            throw new RuntimeException("Only veterinarians can access this functionality.");
        }
    }

    public void validateMedicalRecord(MedicalRecord medicalRecord) {
        if(medicalRecord.getVeterinarianDocumentId() <= 0) {
            throw new IllegalArgumentException("Invalid vet ID.");
        }

        if(medicalRecord.getDate() == null) {
            throw new IllegalArgumentException("The date is mandatory.");
        }

        if(medicalRecord.getConsultationReason() == null || medicalRecord.getConsultationReason().isEmpty()) {
            throw new IllegalArgumentException("The reason for consultation is mandatory.");
        }
    }
}
