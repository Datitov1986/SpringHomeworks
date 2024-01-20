package homeworks.spring.homework3.repository;

import homeworks.spring.homework3.model.Reader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

    private final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        readers.addAll(List.of(
                new Reader("Игорь"),
                new Reader("Илья"),
                new Reader("Антон"),
                new Reader("Василий"),
                new Reader("Вячеслав")
        ));
    }

    public Reader getReaderById(long id) {
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<Reader> getReaders() {
        return List.copyOf(readers);
    }

    public void deleteReader(long id) {
        readers.removeIf(reader -> reader.getId() == id);
    }

    public void createReader(Reader reader) {
        readers.add(reader);
    }


}
