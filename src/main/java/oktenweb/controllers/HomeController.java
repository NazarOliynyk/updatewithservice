package oktenweb.controllers;

import lombok.AllArgsConstructor;
import oktenweb.models.Contact;
import oktenweb.models.Phone;
import oktenweb.services.ContactService;
import oktenweb.services.PhoneService;
import oktenweb.services.editors.PhoneEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private ContactService contactService;
    private PhoneService phoneService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("contact", new Contact("test", "test@test.com"));
        //return "home";
        //return "homeAsync";
        return "homeAsyncImage";
    }
    // we can assemble an object if parameters in html file have the same names as fields in the class(in this case -Contact)
    @PostMapping("/saveContact")
    public String saveContact(@Valid Contact contact, BindingResult bindingResult,
                              @RequestParam("picture") MultipartFile file){

        if(bindingResult.hasErrors()){
            return "home";
        }

        contactService.transferFile(file);
        contact.setAvatar(file.getOriginalFilename());
        contactService.save(contact);
//        Phone phone = contact.getPhoneList().get(0);
//        phone.setContact(contact);
//        phoneService.save(phone);
//        System.out.println(contact);
//        Phone phone = new Phone(num);
//        phone.setContact(contact);
//        phoneService.save(phone);

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

    @Autowired
    private PhoneEditor phoneDoctor;

    @InitBinder("contact")
    public void initBinder(WebDataBinder binder){
        System.out.println("!!!!!!!!!!!!!!!");
//        binder.registerCustomEditor(Phone.class, "phoneList" ,phoneDoctor);
        binder.registerCustomEditor(Phone.class, phoneDoctor);
    }

    //  homeAsyncImage might also work here instead of in CustomAsyncController


//    @Autowired
//    private EmailService emailService;
//    @PostMapping("/upload")
//    public @ResponseBody String uploadAjax(
//            @RequestParam String name,
//            @RequestParam String email,
//            @RequestParam MultipartFile image
//    ){
//        Contact contact = new Contact(name, email);
//        contact.setAvatar(image.getOriginalFilename());
//        contactService.transferFile(image);
//        contactService.save(contact);
//
//        emailService.sendEmail(email);
//        return "ok!"; // it is printed into browser
//    }
//@ResponseBody allovs to not to call page "ok!" at all


}
