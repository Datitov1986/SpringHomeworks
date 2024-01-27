package homeworks.spring.homework4.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Reader() {
    }

    public Reader(String name) {
        this.name = name;
    }

}
