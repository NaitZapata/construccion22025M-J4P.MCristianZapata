package app.ports;

import app.domain.models.Pet;

public interface PetPort {
    boolean existPet(long petId);
    void savePet(Pet pet);
    Pet findByPetId(long petId);
    Pet findByOwnerDocumentId(long ownerDocumentId);
}
