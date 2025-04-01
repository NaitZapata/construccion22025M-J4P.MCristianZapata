package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Veterinarian {
    private long documentId;
    private String username;
    private String password;
    private String specialization;
}
