package ru.semenchenko.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semenchenko.library.models.Book;

/**
 * @author Artyom Semenchenko
 */

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

}
