package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
