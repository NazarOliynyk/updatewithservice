package oktenweb.dao;

import oktenweb.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<CustomUser, Integer> {
    @Query("select u from CustomUser u where u.username=:name")
    CustomUser loadByUsername(@Param("name") String name);
}
