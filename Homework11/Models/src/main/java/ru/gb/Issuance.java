package ru.gb;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность выдачи")
public class Issuance {

    @Getter
    @Setter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор выдачи")
    private Long id;

    @Column(name = "book_id")
    @Schema(description = "Ссылка идентификатор книги")
    private Long bookId;

    @Column(name = "reader_id")
    @Schema(description = "Ссылка идентификатор читателя")
    private Long readerId;
    /**
     * Дата выдачи книги
     */
    @Column(name = "issuance_at")
    @Schema(description = "Дата и время выдачи книги")
    private LocalDateTime issuance_at;
    /**
     * Дата возврата книги
     */
    @Column(name = "returned_at")
    @Schema(description = "Дата и время возврата книги")
    private LocalDateTime returned_at;

    public Issuance(long bookId, long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.issuance_at = LocalDateTime.now();
    }

    public Issuance fromIssuanceTransform(IssuanceDTO issuanceTransform) {
        Issuance issuance = new Issuance();
        issuance.setId(issuanceTransform.getId());
        issuance.setBookId(issuanceTransform.getBook().getId());
        issuance.setReaderId(issuanceTransform.getReader().getId());
        issuance.setIssuance_at(issuanceTransform.getIssuanceAt());
        issuance.setReturned_at(issuanceTransform.getReturnedAt());
        return issuance;
    }

}