package oktenweb.dao;

import oktenweb.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 03.01.19.
 */
public interface PhoneDAO extends JpaRepository<Phone, Integer>{
}
