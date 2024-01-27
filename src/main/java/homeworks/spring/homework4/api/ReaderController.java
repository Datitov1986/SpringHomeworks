package homeworks.spring.homework4.api;

import homeworks.spring.homework4.model.Issue;
import homeworks.spring.homework4.model.Reader;
import homeworks.spring.homework4.repository.ReaderRepository;
import homeworks.spring.homework4.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    private final ReaderRepository readerRepository;
    private final ReaderService readerService;


    public ReaderController(ReaderRepository readerRepository, ReaderService readerService) {
        this.readerRepository = readerRepository;
        this.readerService = readerService;
    }

    @GetMapping("/all")
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable long id) {
        return readerRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable long id) {
        readerRepository.deleteById(id);
    }

    @PostMapping
    public void createReader(@RequestBody Reader reader) {
        readerRepository.save(reader);
    }

    @GetMapping("/{id}/issue")
    public Issue getReaderIssues(@PathVariable long id) {
        return readerService.getIssuesForReader(id);
    }
}
