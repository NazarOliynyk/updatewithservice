package oktenweb.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"phoneList"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Size(min = 3, max = 6, message = "hello {foo.message}")
    String name;
    String email;

    @JsonIgnore   // it is to avoid coupling connected objects in JSON script
    @OneToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                mappedBy = "contact")
    List<Phone> phoneList = new ArrayList<>();

    private String avatar;

    public Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
