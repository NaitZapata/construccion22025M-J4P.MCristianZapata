package app.adapters.pet.repository;

import app.adapters.pet.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {
    boolean existsByPetId(long petId);
    PetEntity findByPetId(long petId);
    PetEntity findByOwnerDocumentId(long ownerDocumentId);
}
