package ru.gb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssuanceDTO {
    private Long id;
    private Book book;
    private Reader reader;
    private LocalDateTime issuanceAt;
    private LocalDateTime returnedAt;
}
