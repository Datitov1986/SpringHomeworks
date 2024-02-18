package homeworks.spring.homework10.model;

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

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;


    public Reader() {
    }

    public Reader(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Reader(String name, String login, String password, String role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public static Reader ofName(String name) {
        Reader reader = new Reader();
        reader.setName(name);
        return reader;
    }
}
