package app.domain.services;

import app.domain.models.Pet;
import app.ports.PetPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private final PetPort petPort;

    @Autowired
    public PetService(PetPort petPort) {
        this.petPort = petPort;
    }

    public boolean checkPetExists(long petId) {
        return petPort.existPet(petId);
    }

    public Pet registerPet(Pet pet) {
        if(checkPetExists(pet.getPetId())) {
            throw new RuntimeException("Pet with this ID already exists.");
        }

        validatePet(pet);
        petPort.savePet(pet);
        return pet;
    }

    public Pet updatePet(Pet pet) {
        if(!checkPetExists(pet.getPetId())) {
            throw new RuntimeException("Pet not found.");
        }

        validatePet(pet);
        petPort.savePet(pet);
        return pet;
    }

    public Pet getPetById(long petId) {
        Pet pet = petPort.findByPetId(petId);
        if(pet == null) {
            throw new RuntimeException("Pet not found with ID: " + petId);
        }
        return pet;
    }

    public Pet getPetByOwnerDocumentId(long ownerDocumentId) {
        return petPort.findByOwnerDocumentId(ownerDocumentId);
    }

    public void validatePet(Pet pet) {
        if(pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw  new IllegalArgumentException("Pet name cannot be empty.");
        }

        if(pet.getOwnerDocumentId() <= 0) {
            throw new IllegalArgumentException("Invalid owner document ID.");
        }

        if(pet.getSpecies() == null || pet.getSpecies().trim().isEmpty()) {
            throw new IllegalArgumentException("Species cannot be empty.");
        }
    }
}
