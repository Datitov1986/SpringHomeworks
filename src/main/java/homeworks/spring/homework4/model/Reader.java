package homeworks.spring.homework4.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "Идентификатор читателя")
    private Long id;

    @Column(name = "name")
    @Schema(name = "Имя читателя")
    private String name;

    public Reader() {
    }

    public Reader(String name) {
        this.name = name;
    }

}
