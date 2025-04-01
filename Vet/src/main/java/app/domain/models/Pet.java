package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Pet {
    private long petId;
    private String name;
    private long ownerDocumentId;
    private int age;
    private String species;
    private String breed;
    private String characteristics;
    private double weight;

    public Pet(long petId, String name, long ownerDocumentId, int age, String species, String breed, String characteristics, double weight) {
        this.petId = petId;
        this.name = name;
        this.ownerDocumentId = ownerDocumentId;
        this.age = age;
        this.species = species;
        this.breed = breed;
        this.characteristics = characteristics;
        this.weight = weight;
    }
}
