package homeworks.spring.homework5.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "Идентификатор книги")
    private Long id;

    @Column(name = "name")
    @Schema(name = "Название книги")
    private String name;

    public Book() {
    }

    public Book(String name) { this.name = name;}

}