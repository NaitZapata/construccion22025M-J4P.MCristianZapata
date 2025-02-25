package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class Person {
    private long id;
    private char name;
    private long document;
    private int age;

    public Person(long id, char name, long document, int age) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.age = age;
    }
}
