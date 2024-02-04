package homeworks.spring.homework5;

import homeworks.spring.homework5.model.Reader;
import homeworks.spring.homework5.repository.ReaderRepository;
import homeworks.spring.homework5.service.ReaderProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ReaderProperties.class)
public class Application {
    public static void main(String[] args) {
        ReaderRepository readerRepository = SpringApplication.run(Application.class, args).getBean(ReaderRepository.class);
        createReader(readerRepository,new Reader("Ivan", "ivan", "123","reader"));
        createReader(readerRepository,new Reader("Anna", "anna", "123","admin"));
        createReader(readerRepository,new Reader("Maria", "maria", "123","reader"));
        createReader(readerRepository,new Reader("Ilia", "ilia", "123","reader"));

    }

    private static void createReader(ReaderRepository repository, Reader reader) {
        repository.save(reader);


    }

}
