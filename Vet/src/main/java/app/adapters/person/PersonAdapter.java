package app.adapters.person;

import app.adapters.person.entity.PersonEntity;
import app.adapters.person.repository.PersonRepository;
import app.domain.models.Person;
import app.ports.PersonPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@Service
public class PersonAdapter implements PersonPort {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean existPerson(long documentId) {
        return personRepository.existsByDocumentId(documentId);
    }

    @Override
    public void savePerson(Person person) {
        PersonEntity personEntity = personAdapter(person);
        personRepository.save(personEntity);
    }

    @Override
    public Person findByDocument(long documentId) {
        PersonEntity personEntity = personRepository.findByDocumentId(documentId);
        return personEntity != null ? personAdapter(personEntity) : null;
    }

    private PersonEntity personAdapter(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setPersonId(person.getPersonId());
        personEntity.setDocumentId(person.getDocumentId());
        personEntity.setName(person.getName());
        personEntity.setAge(person.getAge());
        personEntity.setRole(person.getRole());
        return personEntity;
    }

    private Person personAdapter(PersonEntity personEntity) {
        Person person = new Person(
                personEntity.getDocumentId(),
                personEntity.getName(),
                personEntity.getAge(),
                personEntity.getRole()
        );
        person.setPersonId(personEntity.getPersonId());
        return person;
    }
}
