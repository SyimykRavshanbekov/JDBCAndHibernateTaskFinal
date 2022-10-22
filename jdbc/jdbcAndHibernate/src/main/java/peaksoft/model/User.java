package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ussers")
@ToString
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private Byte age;

    public User(String name, String lastname, Byte age) {
        this.name = name;
        this.lastName = lastname;
        this.age = age;
    }
}