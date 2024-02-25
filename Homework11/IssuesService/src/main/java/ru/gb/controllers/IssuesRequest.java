package ru.gb.controllers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс описывает запрос на выдачу книги
 */
@Data
@Schema(description = "Сущность запроса на выдачу")
@AllArgsConstructor
public class IssuesRequest {
    /**
     * Идентификатор читателя
     */
    @Schema(description = "Ссылка идентификатор читателя")
    private Long readerId;
    /**
     * Идентификатор книги
     */
    @Schema(description = "Ссылка идентификатор книги")
    private Long bookId;
}
