package oktenweb.services.editors;

import oktenweb.models.Phone;
import oktenweb.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class PhoneEditor extends PropertyEditorSupport {

    @Autowired
    private PhoneService phoneService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Phone phone = new Phone();
        phone.setNumber(text);
        phoneService.save(phone);
        setValue(phone);
    }
}
