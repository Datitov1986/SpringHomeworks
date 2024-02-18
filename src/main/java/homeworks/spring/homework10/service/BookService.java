package homeworks.spring.homework10.service;

import com.github.javafaker.Faker;
import homeworks.spring.homework10.api.BookResponse;
import homeworks.spring.homework10.model.Book;
import homeworks.spring.homework10.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @PostConstruct
    void createBooks() {
        Faker faker = new Faker();
        List<Book> listOfBooks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            listOfBooks.add(new Book(faker.book().title()));
        }
        bookRepository.saveAll(listOfBooks);
    }

    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()) {
            throw new NoSuchElementException("Книг нет");
        }
        return books;
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Книга с ID = " + id + " не найдена")
                );
    }

    public Book addNewBook(Book book) {
        if(book.getName().isEmpty()) {
            throw new RuntimeException("Введите название книги");
        }
        return bookRepository.save(book);
    }

    public Book deleteBookById(Long id) {
        Book book = findById(id);
        bookRepository.deleteById(id);
        if(Objects.isNull(book)) {
            throw new NoSuchElementException("Книга с ID = " + id + " не найдена");
        }
        return book;
    }
}
