package homeworks.spring.homework2.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import homeworks.spring.homework2.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

    private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
                new Book("Война и мир"),
                new Book("Метрвые души"),
                new Book("Преступление и наказание"),
                new Book("Ревизор"),
                new Book("Вишневый сад")
        ));
    }

    public List<Book> getAll() {
        return List.copyOf(books);
    }

    public Book getBookById(long id) {
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public void deleteBook(long id) {
        books.removeIf(book -> book.getId() == id);
    }

    public void createBook(Book book) {
        books.add(book);
    }


}
