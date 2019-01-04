package oktenweb.dao;

import oktenweb.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

/**
 * Created by user on 03.01.19.
 */
public interface ContactDAO extends JpaRepository<Contact, Integer> {

    // spring will do it automaticaly, the names of fields must be correct
    List<Contact> findAllByName(String name);

    // the same is here, it is presumed that there are not equal emails
    @Query("select c from Contact c where c.email=:xxx") // do not put ; here!!!!
    Contact customRequestByEmail(@Param("xxx") String email);
}
