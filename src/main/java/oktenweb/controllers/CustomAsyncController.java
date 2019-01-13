package oktenweb.controllers;

import oktenweb.models.Contact;
import oktenweb.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CustomAsyncController {

    @Autowired
    private ContactService contactService;
    //rest controllers work with methods Get and Post which do not redirect to pages

    @PostMapping("/saveAsync")
    public List<Contact> saveAsync(@RequestBody Contact contact){
        contactService.save(contact);
        System.out.println(contact);
        System.out.println("react");

        List<Contact> results = contactService.findAll();
        return results;
    }

    @PostMapping("/upload")
    public
    @ResponseBody
        // allows to not return template
    String uploadAjax(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("image") MultipartFile image
    ){
        Contact contact = new Contact(name, email);
        contact.setAvatar(image.getOriginalFilename());
        contactService.transferFile(image);
        contactService.save(contact);
        return "ok";
    }


}
