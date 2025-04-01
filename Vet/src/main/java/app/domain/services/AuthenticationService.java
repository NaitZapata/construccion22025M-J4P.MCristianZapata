package app.domain.services;

import com.app.domain.models.Person;
import com.app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private PersonPort personPort;

    public boolean authenticateAdministrator(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }

    public boolean authenticateVeterinarian(String username, String password) {
        return true;
    }

    public boolean authenticateSeller(String username, String password) {
        return true;
    }

    public Person getCurrentPerson(String username, String role) {
        return new Person();
    }

    public boolean hasPermission(Long personId, String action) {
        Person person = personPort.findByDocument(personId);
        if(person == null) {
            return false;
        }

        String role = person.getRole();

        return switch (action) {
            case "CREATE_VETERINARIAN":
            case "CREATE_SELLER":
            case "CREATE_ADMINISTRATOR":
            case "VIEW_ADMINISTRATOR":
            case "VIEW_VETERINARIAN":
            case "VIEW_SELLER":
                yield "ADMINISTRATOR".equalsIgnoreCase(role);

            case "VIEW_MEDICAL_RECORD":
            case "CREATE_MEDICAL_RECORD":
            case "CREATE_ORDER":
            case "CANCEL_ORDER":
                yield "VETERINARIAN".equalsIgnoreCase(role);

            case "VIEW_ORDER":
                yield "VETERINARIAN".equalsIgnoreCase(role) || "SELLER".equalsIgnoreCase(role);

            case "CREATE_INVOICE":
            case "VIEW_INVOICE":
                yield "SELLER".equalsIgnoreCase(role);

            default:
                yield false;
        };
    }
}
