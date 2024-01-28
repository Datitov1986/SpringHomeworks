package homeworks.spring.homework4;

import homeworks.spring.homework4.service.ReaderProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
@EnableConfigurationProperties(ReaderProperties.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
