package homeworks.spring.homework10.api;

import homeworks.spring.homework10.model.Issue;
import homeworks.spring.homework10.model.Reader;
import homeworks.spring.homework10.repository.ReaderRepository;
import homeworks.spring.homework10.service.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
@Tag(name = "Читатель")
public class ReaderController {

    private final ReaderRepository readerRepository;
    private final ReaderService readerService;


    public ReaderController(ReaderRepository readerRepository, ReaderService readerService) {
        this.readerRepository = readerRepository;
        this.readerService = readerService;
    }

    @GetMapping("/all")
    @Operation(summary = "Получить список всех читателей", description = "Загрузка списка всех пользователей, получивших читательский билет нашей библиотеки")
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить читателя", description = "Загрузка читателя по его идентификатору")
    public Reader getReaderById(@PathVariable long id) {
        return readerRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить читателя", description = "Удаление читателя по его идентификатору")
    public void deleteReader(@PathVariable long id) {
        readerRepository.deleteById(id);
    }

    @PostMapping
    @Operation(summary = "Создать читателя", description = "Вообще для создания новых читателей библиотека не очень подходит, в данном случае мы регистрируем нового читателя и выдаем ему читательский билет")
    public void createReader(@RequestBody Reader reader) {
        readerRepository.save(reader);
    }

    @PatchMapping
    public void updateReader(@RequestBody Reader reader){
        readerRepository.save(reader);
    }


    @GetMapping("/{id}/issue")
    @Operation(summary = "Получить список выдач", description = "Загрузка списка выдач для читателя с заданным идентификатором")
    public Issue getReaderIssues(@PathVariable long id) {
        return readerService.getIssuesForReader(id);
    }
}
