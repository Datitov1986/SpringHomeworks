package homeworks.spring.homework4.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Book() {
    }

    public Book(String name) { this.name = name;}

}