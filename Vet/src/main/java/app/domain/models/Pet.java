package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Pet {
    private String name;
    private long id;
    private char race;
    private int age;
    private String specie;
    private char features;
    private int weigth;
    private long ownerId;

    public Pet(int weigth, long ownerId, char features, String specie, int age, char race, long id, String name) {
        this.weigth = weigth;
        this.ownerId = ownerId;
        this.features = features;
        this.specie = specie;
        this.age = age;
        this.race = race;
        this.id = id;
        this.name = name;
    }
}
