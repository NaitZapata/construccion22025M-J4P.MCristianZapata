package app.adapters.person.repository;

import app.adapters.person.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    boolean existsByDocumentId(long documentId);
    PersonEntity findByDocumentId(long documentId);
}
