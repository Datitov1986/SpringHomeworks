package homeworks.spring.homework10.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Идентификатор книги")
    private Long id;

    @Column(name = "name")
    @Schema(name = "Название книги")
    private String name;

    public Book() {
    }

    public Book(String name) { this.name = name;}

    public Book (Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Book ofName(String name) {
        Book book = new Book();
        book.setName(name);
        return book;
    }

}