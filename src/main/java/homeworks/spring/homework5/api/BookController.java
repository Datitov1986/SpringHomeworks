package homeworks.spring.homework5.api;

import homeworks.spring.homework5.model.Book;
import homeworks.spring.homework5.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Книги")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/all")
    @Operation(summary = "Получить список всех книг", description = "Загрузка списка всех книг, состоящих на балансе нашей библиотеки")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить книгу по идентификатору", description = "Загрузка книги по её идентификатору")
    public Book getBook(@PathVariable long id) {
        return bookRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление книги", description = "Удаление книги по её идентификатору")
    public void deleteBook(@PathVariable long id) {
        bookRepository.deleteById(id);
    }

    @PostMapping
    @Operation(summary = "Создание книги", description = "Занесение записи о новой книге в библиотеке")
    public void createBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

}
