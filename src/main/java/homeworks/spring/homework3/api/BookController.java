package homeworks.spring.homework3.api;

import homeworks.spring.homework3.model.Book;
import homeworks.spring.homework3.repository.BookRepository;
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
        return bookRepository.getAllBooks();
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
