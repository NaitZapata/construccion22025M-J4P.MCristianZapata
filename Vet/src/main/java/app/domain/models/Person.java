package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Person {
    private long personId;
    private long documentId;
    private String name;
    private int age;
    private String role;

    public Person(long documentId, String name, int age, String role) {
    }
}
