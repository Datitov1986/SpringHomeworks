package homeworks.spring.homework10.api;

import homeworks.spring.homework10.model.Book;
import homeworks.spring.homework10.repository.BookRepository;
import homeworks.spring.homework10.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/book")
@Tag(name = "Книги")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final BookService service;



    @GetMapping("/all")
    @Operation(summary = "Получить список всех книг", description = "Загрузка списка всех книг, состоящих на балансе нашей библиотеки")
    public List<Book> getAllBooks() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить книгу по идентификатору", description = "Загрузка книги по её идентификатору")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        final Book book;
        try {
            book = service.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление книги", description = "Удаление книги по её идентификатору")
    public void deleteBook(@PathVariable long id) {
        bookRepository.deleteById(id);
    }

    @PostMapping
    @Operation(summary = "Создание книги", description = "Занесение записи о новой книге в библиотеке")
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
        final Book book;
        try {
            book = service.addNewBook(newBook);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

}
