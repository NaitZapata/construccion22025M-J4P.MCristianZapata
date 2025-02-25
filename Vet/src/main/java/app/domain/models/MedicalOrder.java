package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor

public class MedicalOrder {
    private long id;
    private long petId;
    private long ownerId;
    private char veterinarian;
    private char doce;
    private Timestamp dateCreated;

    public MedicalOrder(long id, long petId, long ownerId, char veterinarian, char doce, Timestamp dateCreated) {
        this.id = id;
        this.petId = petId;
        this.ownerId = ownerId;
        this.veterinarian = veterinarian;
        this.doce = doce;
        this.dateCreated = dateCreated;
    }
}
