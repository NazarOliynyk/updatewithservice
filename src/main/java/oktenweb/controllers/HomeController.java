package oktenweb.controllers;

import lombok.AllArgsConstructor;
import oktenweb.models.Contact;
import oktenweb.models.Phone;
import oktenweb.services.ContactService;
import oktenweb.services.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private ContactService contactService;
    private PhoneService phoneService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("contatcts", contactService.findAll());
        return "home";
    }
    // we can assemble an object if parameters in html file have the same names as fields in the class(in this case -Contact)
    @PostMapping("/saveContact")
    public String saveContact(Contact contact, @RequestParam("phoneList") String num){
       contactService.save(contact);
//        Phone phone = contact.getPhoneList().get(0);
//        phone.setContact(contact);
//        phoneService.save(phone);
//        System.out.println(contact);
        Phone phone = new Phone(num);
        phone.setContact(contact);
        phoneService.save(phone);

        return "redirect:/";
    }
    
    @GetMapping("/details-{xxx}")
    public String contactDetails(@PathVariable("xxx") int id, Model model){
        Contact one = contactService.getOne(id);
        model.addAttribute("contact", one);
        return "contactDetails";
    }
    
    @PostMapping("/update")
    public String updateContact(Contact contact){
        contactService.save(contact);
        return "redirect:/";
    }
}
