package books.data;

import books.domain.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {

    Collection<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    Book save(Book book);
    void delete(String isbn);

}
