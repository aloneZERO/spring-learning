package contacts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author justZero
 */
@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ContactController {

    private ContactRepository contactRepo;

    @GetMapping
    public String home(Map<String, Object> model) {
        List<Contact> contacts = contactRepo.findAll();
        model.put("contacts", contacts);
        return "home";
    }

    @PostMapping
    public String submit(Contact contact) {
        contactRepo.save(contact);
        return "redirect:/";
    }
}