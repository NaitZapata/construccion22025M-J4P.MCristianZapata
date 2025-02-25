package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Pet {
    private char name;
    private long ownerId;


    public Pet(char name, long ownerId, int age, long id, char species, char breed, char color, double size, double weight, Timestamp dateCreated) {
        this.name = name;
        this.ownerId = ownerId;
        this.age = age;
        this.id = id;
        this.name = name;
    }
}
