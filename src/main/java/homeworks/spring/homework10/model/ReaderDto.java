package homeworks.spring.homework10.model;

import lombok.Data;

@Data
public class ReaderDto {
    private final long id;
    private final String bookName;
    private final String readerName;
    private final Issue readerIssues;
}
