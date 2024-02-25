package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
