package app.domain.services;

import com.app.domain.models.Person;
import com.app.domain.models.Seller;
import com.app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    private PersonPort personPort;

    @Autowired
    private AuthenticationService authenticationService;

    public Person createSeller(Seller seller, Long userDocumentId) {
        if(!authenticationService.hasPermission(userDocumentId, "CREATE_SELLER")) {
            throw new RuntimeException("You don't have permission to create sellers.");
        }

        if(seller.getDocumentId() <= 0) {
            throw new IllegalArgumentException("Invalid document ID.");
        }

        if(seller.getUsername() == null || seller.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }

        if(seller.getPassword() == null || seller.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }

        if(personPort.existPerson(seller.getDocumentId())) {
            throw new RuntimeException("A person with this document ID already exists.");
        }

        Person person = new Person();
        person.setDocumentId(seller.getDocumentId());
        person.setName(seller.getUsername());
        person.setRole("SELLER");

        personPort.savePerson(person);
        return person;
    }

    public Person getSellerByDocumentId(long documentId, Long userDocumentId) {
        if(!authenticationService.hasPermission(userDocumentId, "VIEW_SELLER")) {
            throw new RuntimeException("You don't have permission to view seller information.");
        }

        Person seller = personPort.findByDocument(documentId);
        if(seller == null || !"SELLER".equalsIgnoreCase(seller.getRole())) {
            throw new RuntimeException("Seller not found.");
        }

        return seller;
    }
}
