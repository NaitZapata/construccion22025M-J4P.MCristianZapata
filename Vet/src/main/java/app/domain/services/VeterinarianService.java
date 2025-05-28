package app.domain.services;

import com.app.domain.models.Person;
import com.app.domain.models.Veterinarian;
import com.app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinarianService {
    @Autowired
    private PersonPort personPort;

    @Autowired
    private AuthenticationService authenticationService;

    public Person createVeterinarian(Veterinarian veterinarian, Long userDocumentId) {
        if(!authenticationService.hasPermission(userDocumentId, "CREATE_VETERINARIAN")) {
            throw new RuntimeException("You don't have permission to create veterinarians.");
        }

        if(veterinarian.getDocumentId() <= 0) {
            throw new IllegalArgumentException("Invalid document ID.");
        }

        if(veterinarian.getUsername() == null || veterinarian.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }

        if(veterinarian.getPassword() == null || veterinarian.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        if(personPort.existPerson(veterinarian.getDocumentId())) {
            throw new RuntimeException("A person with this document ID already exists.");
        }

        Person person = new Person();
        person.setDocumentId(veterinarian.getDocumentId());
        person.setName(veterinarian.getUsername());
        person.setRole("VETERINARIAN");

        personPort.savePerson(person);
        return person;
    }

    public Person getVeterinarianByDocumentId(long documentId, Long userDocumentId) {
        if(!authenticationService.hasPermission(userDocumentId, "VIEW_VETERINARIAN")) {
            throw new RuntimeException("You don't have permission to view veterinarian information.");
        }

        Person veterinarian = personPort.findByDocument(documentId);
        if(veterinarian == null || !"VETERINARIAN".equalsIgnoreCase(veterinarian.getRole())) {
            throw new RuntimeException("Veterinarian not found.");
        }
        return veterinarian;
    }
}
