package app.adapters.pet;

import app.adapters.pet.entity.PetEntity;
import app.adapters.pet.repository.PetRepository;
import app.domain.models.Pet;
import app.ports.PetPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@Service
public class PetAdapter implements PetPort {
    @Autowired
    private PetRepository petRepository;

    @Override
    public boolean existPet(long petId) {
        return petRepository.existsByPetId(petId);
    }

    @Override
    public void savePet(Pet pet) {
        PetEntity petEntity = petAdapter(pet);
        petRepository.save(petEntity);
        pet.setPetId(petEntity.getPetId());
    }

    @Override
    public Pet findByPetId(long petId) {
        PetEntity petEntity = petRepository.findByPetId(petId);
        return petEntity != null ? petAdapter(petEntity) : null;
    }

    @Override
    public Pet findByOwnerDocumentId(long ownerDocumentId) {
        PetEntity petEntity = petRepository.findByOwnerDocumentId(ownerDocumentId);
        return petEntity != null ? petAdapter(petEntity) : null;
    }

    private Pet petAdapter(PetEntity petEntity) {
        if(petEntity == null) return null;

        return new Pet(
                petEntity.getPetId(),
                petEntity.getName(),
                petEntity.getOwnerDocumentId(),
                petEntity.getAge(),
                petEntity.getSpecies(),
                petEntity.getBreed(),
                petEntity.getCharacteristics(),
                petEntity.getWeight()
        );
    }

    private PetEntity petAdapter(Pet pet) {
        PetEntity petEntity = new PetEntity();
        petEntity.setPetId(pet.getPetId());
        petEntity.setName(pet.getName());
        petEntity.setOwnerDocumentId(pet.getOwnerDocumentId());
        petEntity.setAge(pet.getAge());
        petEntity.setSpecies(pet.getSpecies());
        petEntity.setBreed(pet.getBreed());
        petEntity.setWeight(pet.getWeight());
        return petEntity;
    }
}
