package io.github.ren1208.digital_library.repositories;

import io.github.ren1208.digital_library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Artyom Semenchenko
 */

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameStartingWith(String name);
}
