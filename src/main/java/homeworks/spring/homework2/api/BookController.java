package homeworks.spring.homework2.api;

import homeworks.spring.homework2.model.Book;
import homeworks.spring.homework2.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return bookRepository.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookRepository.deleteBook(id);
    }

    @PostMapping
    public void createBook(@RequestBody Book book) {
        bookRepository.createBook(book);
    }

}
