package ru.semenchenko.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.semenchenko.library.models.Book;
import ru.semenchenko.library.models.Person;
import ru.semenchenko.library.repositories.BooksRepository;

import java.util.List;

/**
 * @author Artyom Semenchenko
 */

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortedByYear) {
        if (sortedByYear)
            return booksRepository.findAll(Sort.by("yearOfRelease"));
        else
            return booksRepository.findAll();
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    updatedBook.setBookId(id);
                    updatedBook.setOwner(book.getOwner());

                    booksRepository.save(updatedBook);
                });
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Person getBookOwner(int id) {
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                });
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectedPerson);
                }
        );
    }

    public List<Book> findWithPagination(Integer page,
                                         Integer numberOfBooksPerPage,
                                         boolean sortedByYear) {
        if (sortedByYear)
            return booksRepository.findAll(PageRequest.of(page, numberOfBooksPerPage,
                    Sort.by("yearOfRelease"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, numberOfBooksPerPage)).getContent();
    }

}
