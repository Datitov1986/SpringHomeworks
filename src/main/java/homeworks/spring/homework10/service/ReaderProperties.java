package homeworks.spring.homework10.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application.reader")
public class ReaderProperties {
    private Integer maxAllowedBook;
}
