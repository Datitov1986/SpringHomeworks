package homeworks.spring.homework4.api;

import homeworks.spring.homework4.model.Book;
import homeworks.spring.homework4.repository.BookRepository;
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
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return bookRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookRepository.deleteById(id);
    }

    @PostMapping
    public void createBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

}
