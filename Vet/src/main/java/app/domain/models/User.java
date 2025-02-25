package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class User {
    private long id;
    private char userName;
    private char password;
    private char role;
    private long personId;

    public User(long id, char userName, char password, char role, long personId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.personId = personId;
    }
}
