package app.ports;

import app.domain.models.Person;

public interface PersonPort {
    boolean existPerson(long documentId);
    void savePerson(Person person);
    Person findByDocument(long documentId);
}
