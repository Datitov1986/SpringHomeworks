package homeworks.spring.homework4.model;

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
    private Long id;
    @Column(name = "bookId")
    private long bookId;
    @Column(name = "readerId")
    private long readerId;

    /**
     * Дата выдачи
     */
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public Issue() {}

    public Issue(long bookId, long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = LocalDateTime.now();
    }


}
