package app.adapters.pet.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class PetEntity {
    @Id
    @Column(name = "pet_id", unique = true)
    private long petId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "owner_document_id", nullable = false)
    private long ownerDocumentId;

    @Column(name = "age")
    private int age;

    @Column(name = "species", nullable = false)
    private String species;

    @Column(name = "breed")
    private String breed;

    @Column(name = "characteristics")
    private String characteristics;

    @Column(name = "weight")
    private double weight;
}
