package homeworks.spring.homework4.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueDto {
    private final long id;
    private final String bookName;
    private final String readerName;
    private final LocalDateTime timestamp;
}
