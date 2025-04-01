package app.domain.services;

import com.app.domain.models.Administrator;
import com.app.domain.models.Person;
import com.app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
    @Autowired
    private PersonPort personPort;

    @Autowired
    private AuthenticationService authenticationService;

    public Person createAdministrator(Administrator administrator, Long userDocumentId) {
        var currentUser = personPort.findByDocument(userDocumentId);
        if(currentUser == null || !"ADMINISTRATOR".equalsIgnoreCase(currentUser.getRole())) {
            throw new RuntimeException("Only administrators can create new administrators.");
        }

        if(administrator.getDocumentId() <= 0) {
            throw new IllegalArgumentException("Invalid document ID.");
        }

        if(administrator.getUsername() == null || administrator.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }

        if(administrator.getPassword() == null || administrator.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }

        if(personPort.existPerson(administrator.getDocumentId())) {
            throw new RuntimeException("A person with this document ID already exists.");
        }

        Person person = new Person();
        person.setDocumentId(administrator.getDocumentId());
        person.setName(administrator.getUsername());
        person.setRole("ADMINISTRATOR");

        personPort.savePerson(person);
        return person;
    }

    public Person getAdministratorByDocumentId(long documentId, Long userDocumentId) {
        if(!authenticationService.hasPermission(userDocumentId, "VIEW_ADMINISTRATOR")) {
            throw new RuntimeException("You don't have permission to view administrator information.");
        }

        Person administrator = personPort.findByDocument(documentId);
        if(administrator == null || !"ADMINISTRATOR".equalsIgnoreCase(administrator.getRole())) {
            throw new RuntimeException("Administrator not found.");
        }

        return administrator;
    }
}
