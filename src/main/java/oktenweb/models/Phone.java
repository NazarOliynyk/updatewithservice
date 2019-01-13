package oktenweb.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"contact"})
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String number;
    @ManyToOne(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    Contact contact;

//    public Phone(String number) {
//        this.number = number;
//    }
}
