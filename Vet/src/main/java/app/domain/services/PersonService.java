package app.domain.services;

import com.app.domain.models.Person;
import com.app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonService {
    private final PersonPort personPort;

    @Autowired
    public PersonService(PersonPort personPort) {
        this.personPort = personPort;
    }

    public boolean checkPersonExists(long documentId) {
        return personPort.existPerson(documentId);
    }

    public Person registerPerson(Person person) {
        if(checkPersonExists(person.getDocumentId())) {
            throw new RuntimeException("Person with this document already exists");
        }

        personPort.savePerson(person);
        return person;
    }

    public Person updatePerson(Person person) {
        if(!checkPersonExists(person.getDocumentId())) {
            throw new RuntimeException("Person not found");
        }

        personPort.savePerson(person);
        return person;
    }

    private void validatePerson(Person person) {
        if(person.getName() == null || person.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if(person.getDocumentId() <= 0) {
            throw new IllegalArgumentException("Invalid document number");
        }
    }
}