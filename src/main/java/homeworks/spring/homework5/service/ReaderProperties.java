package homeworks.spring.homework5.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application.reader")
public class ReaderProperties {
    private Integer maxAllowedBook;
}
