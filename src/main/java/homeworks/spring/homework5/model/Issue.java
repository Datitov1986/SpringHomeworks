package homeworks.spring.homework5.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@Table(name = "issues")
public class Issue {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "Идентификатор записи")
    private Long id;
    @Column(name = "bookId")
    @Schema(name = "Идентификатор книги")
    private long bookId;
    @Column(name = "readerId")
    @Schema(name = "Идентификатор читателя")
    private long readerId;

    /**
     * Дата выдачи
     */
    @Column(name = "timestamp")
    @Schema(name = "Время выдачи")
    private LocalDateTime timestamp;

    public Issue() {}

    public Issue(long bookId, long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = LocalDateTime.now();
    }


}
