package oktenweb.services;

import oktenweb.dao.PhoneDAO;
import oktenweb.models.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

    // the annotation autowired is unnecessary
    private PhoneDAO phoneDAO;

    public PhoneService(PhoneDAO phoneDAO) {
        this.phoneDAO = phoneDAO;
    }

    public void save(Phone phone){

        phoneDAO.save(phone);
    }
}
